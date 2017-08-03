package com.example.lif.kotlindemo.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by lif on 2017/8/3.
 */

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}
