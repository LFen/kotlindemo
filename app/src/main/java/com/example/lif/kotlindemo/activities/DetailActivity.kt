package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.domain.server.RequestDayForecastCommand
import com.example.lif.kotlindemo.extensions.color
import com.example.lif.kotlindemo.extensions.textColor
import com.example.lif.kotlindemo.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.text.DateFormat

/**
 * Created by lif on 2017/8/3.
 */

class DetailActivity: BaseActivity() {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra(CITY_NAME)

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    private fun bindForecast(forecast: DomainClasses.Forecast) = with(forecast) {
        Glide.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}ยบ"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}