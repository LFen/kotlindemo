package com.example.lif.kotlindemo.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by lif on 2017/8/3.
 */

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)