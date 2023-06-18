package com.photoweather.app.features.addPost.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.photoweather.domain.addPost.local.models.City
import com.photoweather.app.databinding.ItemCityBinding
import com.photoweather.app.features.addPost.fragments.CitiesFragment
import com.photoweather.app.util.eventListners.RecycleViewEventListener


class CityHolderItem(
    private val binding: ItemCityBinding,
    private val clickEventListener: RecycleViewEventListener,
    private val holderFragment: CitiesFragment
):RecyclerView.ViewHolder(binding.root) {

    fun bind(data: City){
        binding.apply {
            tvTitle.text=data.name
            root.setOnClickListener {
                clickEventListener.onClick(data.name)
                holderFragment.dismiss()
            }
        }

    }

}