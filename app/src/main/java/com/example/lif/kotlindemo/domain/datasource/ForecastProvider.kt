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
            = sources.firstResult { requestSource(it, zipCode, days) }

    private fun requestSource(source: ForecastDataSource, zipCode: Long, days: Int): DomainClasses.ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size() >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}