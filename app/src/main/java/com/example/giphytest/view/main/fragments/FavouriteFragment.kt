package com.example.giphytest.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphytest.databinding.FragmentFavouriteBinding
import com.example.giphytest.utils.Constants
import com.example.giphytest.utils.hide
import com.example.giphytest.utils.show
import com.example.giphytest.view.main.MainViewModel


class FavouriteFragment : Fragment() {

    private lateinit var binding : FragmentFavouriteBinding
    private var viewModel : MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
        setLiveDataObservers()
        viewModel?.getSavedGiphy()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            viewModel?.getSavedGiphy()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvGiphy.apply {
            layoutManager = GridLayoutManager(requireContext(), Constants.COLUMN_COUNT)
        }
    }

    private fun setLiveDataObservers() {
        viewModel?.savedGiphyLiveData?.observe(this, Observer { list->
            setupViewsForList(list.isNullOrEmpty())
            list?.let{
                binding.rvGiphy.apply {
                    adapter = FavouriteListAdapter(requireContext(),viewModel).apply { setList(list) }
                }
            }
        })?: kotlin.run { setupViewsForList(isListEmpty = false) }
    }

    private fun setupViewsForList(isListEmpty : Boolean = false){
        if(isListEmpty){
            binding.rvGiphy.hide()
            binding.tvError.show()
        }else {
            binding.rvGiphy.show()
            binding.tvError.hide()
        }
        binding.pb.hide()
    }

}