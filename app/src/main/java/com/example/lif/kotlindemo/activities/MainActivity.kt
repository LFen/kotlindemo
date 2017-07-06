package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.lif.kotlindemo.adapters.ForecastListAdapter
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.Request
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import java.util.concurrent.Executors

class MainActivity : BaseActivity() {

    private val testUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7&APPID=15646a06818f61f7b8d7823ca833e1ce&q=020"
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById(R.id.message) as TextView
        textView.text = getString(R.string.hello)

        // ----------- //
        val weatherList : RecyclerView = find(R.id.weather_list)  //by using Anko
        weatherList.layoutManager = LinearLayoutManager(this)
        weatherList.adapter = ForecastListAdapter(items)

//        val person = Person()
//        person.name = "li"
//        showToast(person.name)
//        toast(person.name)  //by using Anko
        val executor = Executors.newScheduledThreadPool(4)
        async(executor) {
            Request(testUrl).run()
        }
    }
}
