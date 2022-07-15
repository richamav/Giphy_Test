package com.example.giphytest.view.main.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphytest.R
import com.example.giphytest.databinding.ListItemBinding
import com.example.giphytest.model.GiphyInfo
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.view.main.MainViewModel
import com.like.LikeButton
import com.like.OnLikeListener


class ListAdapter(val context: Context, val viewModel: MainViewModel?) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    var giphyList : ArrayList<GiphyInfo> = ArrayList()
    lateinit var binding : ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ListViewHolder(context,binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val giphyInfo = giphyList[position]
        holder.bind(giphyInfo)
        binding.starButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                giphyList[position].full = true
                giphyInfo.images?.downsizedLarge?.url?.let{
                    viewModel?.saveGiphy(SavedGifInfo(giphyInfo.id, it))
                }
            }
            override fun unLiked(likeButton: LikeButton) {
                giphyList[position].full = false
                giphyInfo.images?.downsizedLarge?.url?.let{
                    viewModel?.deleteGiphy(SavedGifInfo(giphyInfo.id, it))
                }
            }
        })

    }

    override fun getItemViewType(position: Int)= position

    override fun getItemCount() = giphyList.size


    fun updateList(list: List<GiphyInfo>) {
        giphyList.addAll(list)
        notifyDataSetChanged()
    }

    fun setList(list: List<GiphyInfo>) {
        giphyList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }



    class ListViewHolder(val context : Context,val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivGiphy = itemView.findViewById<ImageView>(R.id.ivGiphy)

        fun bind(giphyInfo: GiphyInfo) {
            giphyInfo.images?.downsizedLarge?.url?.let {url->
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.empty)
                    .into(ivGiphy)
            }

        }
    }
}