package com.example.lif.kotlindemo.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.lif.kotlindemo.adapters.ForecastListAdapter
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.domain.server.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherList.layoutManager = LinearLayoutManager(this)
        doAsync {
            val result = RequestForecastCommand(86020).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                weatherList.adapter = adapter
                title = "${result.city} (${result.country})"
            }
        }
    }
}
