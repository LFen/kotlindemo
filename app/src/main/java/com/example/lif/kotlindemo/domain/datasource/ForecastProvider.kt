package com.example.lif.kotlindemo.domain.datasource

import com.example.lif.kotlindemo.db.ForecastDb
import com.example.lif.kotlindemo.db.server.ForecastServer
import com.example.lif.kotlindemo.domain.DomainClasses
import com.example.lif.kotlindemo.extensions.firstResult

/**
 * Created by lif on 2017/7/18.
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): DomainClasses.ForecastList
            = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): DomainClasses.Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}