package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.lif.kotlindemo.adapters.ForecastListAdapter
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.domain.server.RequestForecastCommand
import com.example.lif.kotlindemo.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : BaseActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        weatherList.layoutManager = LinearLayoutManager(this)
        attachScroll(weatherList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }
            weatherList.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}
