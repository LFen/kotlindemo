package com.example.lif.kotlindemo

import android.app.Application
import com.example.lif.kotlindemo.extensions.DelegatesExt

/**
 * Created by lif on 2017/7/6.
 */

class App: Application() {

    companion object {
        var instance:App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}