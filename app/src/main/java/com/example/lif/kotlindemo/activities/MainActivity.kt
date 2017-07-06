package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.lif.kotlindemo.adapters.ForecastListAdapter
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.adapters.ForecastListAdapter.OnItemClickListener
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.server.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById(R.id.message) as TextView
        textView.text = getString(R.string.hello)

        // ----------- //
        val weatherList : RecyclerView = find(R.id.weather_list)  //by using Anko
        weatherList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("020").execute()
            uiThread {
                weatherList.adapter = ForecastListAdapter(result, object: OnItemClickListener{
                    override fun invoke(forecast: DomainClasses.Forecast) {
                        with(forecast) {
                            toast(date)
                        }
                    }
                })
            }
        }
    }
}
