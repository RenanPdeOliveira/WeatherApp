package com.example.weatherapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.cities.City

class SearchBarListAdapter(
    private val changeCity: (city: City) -> Unit
): ListAdapter<City, SearchBarListAdapter.SearchBarViewHolder>(SearchBarListAdapter) {

    inner class SearchBarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val tvCityName: TextView = view.findViewById(R.id.textViewCityNameSearchBar)

        fun bind(
            item: City,
            changeCity: (city: City) -> Unit
        ) {
            tvCityName.text = item.city
            tvCityName.setOnClickListener {
                changeCity.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_bar, parent, false)
        return SearchBarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchBarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, changeCity)
    }

    companion object : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.city == newItem.city &&
                    oldItem.lat == newItem.lat &&
                    oldItem.long == newItem.long
        }
    }
}