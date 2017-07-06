package com.example.lif.kotlindemo.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.lif.kotlindemo.domain.DomainClasses

/**
 * Created by lif on 2017/7/5.
 */

class ForecastListAdapter (val weekForecastList: DomainClasses.ForecastList)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = weekForecastList.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(weekForecastList[position]) {
            holder?.textView?.text = "$date - $description - $high/$low"
        }
    }

    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
}
