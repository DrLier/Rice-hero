package com.example.capstone.mainpage.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.api.ListItem
import com.example.capstone.databinding.ItemWeatherBinding
import java.util.Date

class WeatherAdapter(private val listItem : List<ListItem>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listItem[position]
        val date = Date(data.dt.toLong() * 1000)
        val celcius = (data.main.temp - 273).toInt()
        holder.binding.apply {
            day.text = date.toString()
            temperature.text = celcius.toString()
            for (mainData in data.weather) {
                information.text = mainData.description
                val image = ("https://openweathermap.org/img/w/" + mainData.icon + ".png")
                Glide.with(holder.itemView.context).load(image).into(iconWeather)
            }
        }
    }

    override fun getItemCount() = 10
}