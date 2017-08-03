package com.example.lif.kotlindemo.domain.datasource

import com.example.lif.kotlindemo.domain.DomainClasses

/**
 * Created by lif on 2017/7/18.
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): DomainClasses.ForecastList?
    fun requestDayForecast(id: Long): DomainClasses.Forecast?
}