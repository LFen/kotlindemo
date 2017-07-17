package com.example.lif.kotlindemo.db

import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.extensions.clear
import com.example.lif.kotlindemo.extensions.parseList
import com.example.lif.kotlindemo.extensions.parseOpt
import com.example.lif.kotlindemo.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by lif on 2017/7/17.
 */

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dbDataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{DayForecast(HashMap(it))}
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt {CityForecast(HashMap(it), dailyForecast)}
        if (city != null) dbDataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: DomainClasses.ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with(dbDataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}