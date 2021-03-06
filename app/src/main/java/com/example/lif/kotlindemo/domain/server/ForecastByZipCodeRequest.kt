package com.example.lif.kotlindemo.domain.server

import com.example.lif.kotlindemo.Models.ServerClasses
import com.google.gson.Gson
import java.net.URL

/**
 * Created by lif on 2017/7/5.
 */

class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${URL}&APPID=${APP_ID}&q="
    }

    fun execute(): ServerClasses.ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return gson.fromJson(forecastJsonStr, ServerClasses.ForecastResult::class.java)
    }
}
