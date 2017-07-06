package com.example.lif.kotlindemo.domain

import com.example.lif.kotlindemo.Models.ResponseClasses
import java.text.DateFormat
import java.util.*
import com.example.lif.kotlindemo.domain.DomainClasses.Forecast as ModelForecast

/**
 * Created by lif on 2017/7/6.
 */

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ResponseClasses.ForecastResult): DomainClasses.ForecastList {
        return DomainClasses.ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ResponseClasses.Forecast>): List<DomainClasses.Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ResponseClasses.Forecast): DomainClasses.Forecast {
        return DomainClasses.Forecast(converData(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun converData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

}