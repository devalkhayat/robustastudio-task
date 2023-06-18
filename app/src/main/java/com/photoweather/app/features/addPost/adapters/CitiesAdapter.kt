package com.photoweather.app.features.addPost.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.photoweather.domain.addPost.local.models.City
import com.photoweather.app.databinding.ItemCityBinding
import com.photoweather.app.features.addPost.adapters.holders.CityHolderItem
import com.photoweather.app.features.addPost.fragments.CitiesFragment
import com.photoweather.app.util.eventListners.RecycleViewEventListener

class CitiesAdapter(private val holderFragment:CitiesFragment,private val onClickListener: RecycleViewEventListener): RecyclerView.Adapter<CityHolderItem>()  {


    private lateinit var items:MutableList<City>
    private lateinit var itemBinding: ItemCityBinding

    fun setItemsList(_items:ArrayList<City>) {

        items = _items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolderItem {
        itemBinding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityHolderItem(itemBinding,onClickListener,holderFragment)
    }

    override fun onBindViewHolder(holder: CityHolderItem, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}