package com.example.lif.kotlindemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.ctx
import com.example.lif.kotlindemo.domain.DomainClasses
import org.jetbrains.anko.find

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

        private val iconView: ImageView = view.find(R.id.icon)
        private val dateView: TextView = view.find(R.id.date)
        private val descriptionView: TextView = view.find(R.id.description)
        private val maxTempView: TextView = view.find(R.id.maxTemperature)
        private val minTempView: TextView = view.find(R.id.minTemperature)

        fun bindForecast(forecast: DomainClasses.Forecast) {
            with(forecast) {
                Glide.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTempView.text = "$high"
                minTempView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: DomainClasses.Forecast)
    }
}
