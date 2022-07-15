package com.example.giphytest.view.main.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphytest.R
import com.example.giphytest.databinding.FavouriteItemBinding
import com.example.giphytest.model.SavedGifInfo
import com.example.giphytest.view.main.MainViewModel


class FavouriteListAdapter(val context: Context, val viewModel: MainViewModel?) : RecyclerView.Adapter<FavouriteListAdapter.ListViewHolder>(){

    var giphyList : ArrayList<SavedGifInfo> = ArrayList()
    lateinit var binding : FavouriteItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ListViewHolder(context,binding.root)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val giphyInfo = giphyList[position]
        holder.bind(giphyInfo)
        binding.ivHeart.setOnClickListener {
            viewModel?.deleteGiphy(giphyInfo)
        }
    }

    override fun getItemViewType(position: Int)= position

    override fun getItemCount() = giphyList.size


    fun setList(list: List<SavedGifInfo>?) {
        giphyList = ArrayList()
        list?.let{giphyList.addAll(list)}
        notifyDataSetChanged()
    }

    class ListViewHolder(val context : Context,val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivGiphy = itemView.findViewById<ImageView>(R.id.ivGiphy)

        fun bind(giphyInfo: SavedGifInfo) {
            giphyInfo.url.let {url->
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.empty)
                    .into(ivGiphy)
            }
        }
    }
}