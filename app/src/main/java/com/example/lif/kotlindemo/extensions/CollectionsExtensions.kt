package com.example.lif.kotlindemo.extensions

/**
 * Created by lif on 2017/7/17.
 */

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()