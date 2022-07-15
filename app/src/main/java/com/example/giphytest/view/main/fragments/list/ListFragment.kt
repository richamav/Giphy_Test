package com.example.giphytest.view.main.fragments.list


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphytest.R
import com.example.giphytest.databinding.FragmentListBinding
import com.example.giphytest.model.GiphyInfo
import com.example.giphytest.utils.hide
import com.example.giphytest.utils.show
import com.example.giphytest.view.main.MainActivity
import com.example.giphytest.view.main.MainViewModel


class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var listAdapter : ListAdapter
    private var viewModel : MainViewModel? = null

    var trendingGiphyListLocal : ArrayList<GiphyInfo> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
        listAdapter = ListAdapter(requireContext(), viewModel)
        setLiveDataObservers()
        viewModel?.getTrendyGiphy()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvGiphy.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setupFabDisplayWrtRecyclerView()
        binding.fab.setOnClickListener { binding.rvGiphy.scrollToPosition(0) }
    }

    private fun setupFabDisplayWrtRecyclerView() {
        binding.rvGiphy.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(binding.rvGiphy, dx, dy)
                binding.fab.visibility =  when ((binding.rvGiphy.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()) {
                    0-> View.GONE
                    else -> View.VISIBLE
                }
            }
        })
    }

    private fun setLiveDataObservers() {
        viewModel?.giphyResponseLiveData?.observe(this, Observer { list ->
            setupViewsForList(list.isNullOrEmpty())
            list?.let {
                listAdapter.setList(list)
                trendingGiphyListLocal.apply {
                    clear()
                    addAll(list)
                }
            } ?: kotlin.run { setupViewsForList(true) }

        })

        viewModel?.searchedGiphyResponseLiveData?.observe(this, Observer { list ->
            list?.let {
                listAdapter.setList(list)
            } ?: kotlin.run { binding.tvError.show() }
            binding.pb.hide()
        })
    }

    fun setupViewsForList(isListEmpty : Boolean ){
        if(isListEmpty){
            binding.tvError.show()
            binding.rvGiphy.hide()
        }else{
            binding.tvError.hide()
            binding.rvGiphy.show()
        }
        binding.pb.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_option_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = SearchView((context as MainActivity).supportActionBar!!.themedContext)

        searchView.setQueryHint(getString(R.string.search_giphy))
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel?.getSearchGiphy(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.isNullOrEmpty() ){
                    if(trendingGiphyListLocal.isNullOrEmpty()) {
                        viewModel?.getTrendyGiphy()
                    }else{
                        listAdapter.setList(trendingGiphyListLocal)
                    }
                }
                return false
            }
        })

        val closeButton : ImageView? = searchView.findViewById<ImageView>(R.id.search_close_btn)
        closeButton?.setOnClickListener {
            listAdapter.setList(trendingGiphyListLocal)
        }
    }
}
