package com.photoweather.app.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.app.databinding.ItemPostBinding
import com.photoweather.app.features.home.adapters.holders.PostHolderItem
import com.photoweather.app.util.eventListners.RecycleViewEventListener

class PostsAdapter(private val onClickListener: RecycleViewEventListener):PagingDataAdapter<PostsEntity, PostHolderItem>(DIFF_UTIL) {

    override fun onBindViewHolder(holder: PostHolderItem, position: Int) {
        holder.bind(getItem(position)!!)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolderItem {
        val itemBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolderItem(parent.context,itemBinding,onClickListener)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PostsEntity>() {
            override fun areItemsTheSame(oldItem: PostsEntity, newItem: PostsEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PostsEntity, newItem: PostsEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}