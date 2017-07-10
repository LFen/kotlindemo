package com.example.lif.kotlindemo.db

/**
 * Created by Administrator on 2017/7/10 0010.
 */

object CityForecastTable {
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

object DayForecastTable {
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "icon_url"
    val CITY_ID = "city_id"
}