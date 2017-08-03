package com.example.lif.kotlindemo.db

import com.example.lif.kotlindemo.domain.DomainClasses

/**
 * Created by lif on 2017/7/17.
 */

class DbDataMapper {

    fun convertFromDomain(forecast: DomainClasses.ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: DomainClasses.Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        DomainClasses.ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        DomainClasses.Forecast(_id, date, description, high, low, iconUrl)
    }
}