package com.example.lif.kotlindemo.server

import com.example.lif.kotlindemo.domain.DomainClasses

/**
 * Created by lif on 2017/7/6.
 */

class RequestForecastCommand(val zipCode: String): com.example.lif.kotlindemo.domain.Command<DomainClasses.ForecastList> {

    override fun execute(): com.example.lif.kotlindemo.domain.DomainClasses.ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return com.example.lif.kotlindemo.domain.ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}