package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.lif.kotlindemo.adapters.ForecastListAdapter
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.domain.server.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : BaseActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        weatherList.layoutManager = LinearLayoutManager(this)
        attachScroll(weatherList)
        doAsync {
            val result = RequestForecastCommand(86020).execute()
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
}
