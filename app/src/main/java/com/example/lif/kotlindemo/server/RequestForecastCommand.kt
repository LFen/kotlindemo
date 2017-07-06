package com.example.lif.kotlindemo.server

import com.example.lif.kotlindemo.domain.Command
import com.example.lif.kotlindemo.domain.DomainClasses

/**
 * Created by lif on 2017/7/6.
 */

class RequestForecastCommand(private val zipCode: String): Command<DomainClasses.ForecastList> {

    override fun execute(): DomainClasses.ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return com.example.lif.kotlindemo.domain.ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}