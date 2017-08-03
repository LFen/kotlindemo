package com.example.lif.kotlindemo.domain.server

import com.example.lif.kotlindemo.domain.Command
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.domain.datasource.ForecastProvider

/**
 * Created by lif on 2017/8/3.
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<DomainClasses.Forecast> {


    override fun execute(): DomainClasses.Forecast = forecastProvider.requestForecast(id)
}