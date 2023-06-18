package com.photoweather.app.features.home.adapters.holders

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.app.databinding.ItemPostBinding
import com.photoweather.app.util.eventListners.RecycleViewEventListener
import com.photoweather.app.util.helper

class PostHolderItem(private val _context:Context,
                     private val binding: ItemPostBinding,
                     private val clickEventListener: RecycleViewEventListener
): RecyclerView.ViewHolder(binding.root) {

    private  val TAG = "PostHolderItem"
    fun bind(data: PostsEntity){
        binding.apply {
           helper.loadImage(_context,data.smallImageLocation,img)
            Log.d(TAG, "bind: fired")
            root.setOnClickListener {
                clickEventListener.onClick(data)
            }
            btnPreview.setOnClickListener {
                clickEventListener.onClick(data)
            }
        }

    }
}