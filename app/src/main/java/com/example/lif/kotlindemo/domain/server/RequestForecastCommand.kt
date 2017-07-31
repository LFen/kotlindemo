package com.example.lif.kotlindemo.domain.server

import com.example.lif.kotlindemo.domain.Command
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.domain.datasource.ForecastProvider

/**
 * Created by lif on 2017/7/6.
 */

class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()): Command<DomainClasses.ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): DomainClasses.ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}