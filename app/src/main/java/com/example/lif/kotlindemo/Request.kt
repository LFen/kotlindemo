package com.example.lif.kotlindemo

import android.util.Log
import java.net.URL

/**
 * Created by lif on 2017/7/5.
 */

class Request(val url: String) {

    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}
