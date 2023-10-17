package com.example.weatherapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

class WeatherItemListAdapter : ListAdapter<WeatherData, WeatherItemListAdapter.WeatherItemViewHolder>(WeatherItemListAdapter) {

    inner class WeatherItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val tvTime: TextView = view.findViewById(R.id.textViewItemTime)
        private val ivIcon: ImageView = view.findViewById(R.id.imageViewItemIcon)
        private val tvTemperature: TextView = view.findViewById(R.id.textViewItemTemperature)

        fun bind(
            item: WeatherData
        ) {
            tvTime.text = item.time.format(DateTimeFormatter.ofPattern("HH:mm"))
            ivIcon.load(item.weatherType.icon)
            tvTemperature.text = "${item.temperature}ºC"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
        return WeatherItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object : DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem.time == newItem.time &&
                    oldItem.weatherType.icon == newItem.weatherType.icon &&
                    oldItem.temperature == newItem.temperature
        }
    }
}