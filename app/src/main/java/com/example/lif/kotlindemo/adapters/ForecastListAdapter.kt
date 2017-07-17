package com.example.lif.kotlindemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.extensions.ctx
import com.example.lif.kotlindemo.domain.DomainClasses
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*

/**
 * Created by lif on 2017/7/5.
 */

class ForecastListAdapter (val weekForecastList: DomainClasses.ForecastList,
                           val itemClick: (DomainClasses.Forecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = weekForecastList.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent?.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindForecast(weekForecastList[position])
    }

    class ViewHolder(view: View, val itemClick: (DomainClasses.Forecast) -> Unit): RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: DomainClasses.Forecast) {
            with(forecast) {
                Glide.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = convertDate(date)
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

        private fun convertDate(date: Long): String {
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return df.format(date)
        }
    }
}
