package com.example.lif.kotlindemo.domain

import com.example.lif.kotlindemo.Models.ServerClasses
import java.text.DateFormat
import java.util.*
import com.example.lif.kotlindemo.domain.DomainClasses.Forecast as ModelForecast

/**
 * Created by lif on 2017/7/6.
 */

class ServerDataMapper {

    fun convertFromDataModel(zipCode: Long, forecast: ServerClasses.ForecastResult): DomainClasses.ForecastList {
        return DomainClasses.ForecastList(zipCode, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ServerClasses.Forecast>): List<DomainClasses.Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ServerClasses.Forecast) = with(forecast) {
        DomainClasses.Forecast(dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun converData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

}