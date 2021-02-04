package com.example.my_apilivedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>(){

    private val mData = ArrayList<WeatherItems>()

    fun setData(items:ArrayList<WeatherItems>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItems:WeatherItems){
            with(itemView){
                textKota.text = weatherItems.name
                textTemp.text = weatherItems.temperature
                textDesc.text = weatherItems.description
            }
        }
    }
}