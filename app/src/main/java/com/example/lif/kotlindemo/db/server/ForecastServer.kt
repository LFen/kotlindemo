package com.example.lif.kotlindemo.db.server

import com.example.lif.kotlindemo.db.ForecastDb
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.domain.ServerDataMapper
import com.example.lif.kotlindemo.domain.datasource.ForecastDataSource
import com.example.lif.kotlindemo.domain.server.ForecastByZipCodeRequest

/**
 * Created by lif on 2017/7/18.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): DomainClasses.ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertFromDataModel(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}