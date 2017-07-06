package com.example.lif.kotlindemo.domain

/**
 * Created by lif on 2017/7/6.
 */

class DomainClasses {

    data class ForecastList(val city: String, val country: String,
                            val dailyForecast:List<Forecast>)

    data class Forecast(val date: String, val description: String, val high: Int,
                        val low: Int)
}