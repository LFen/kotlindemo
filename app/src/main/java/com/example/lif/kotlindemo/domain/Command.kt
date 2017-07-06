package com.example.lif.kotlindemo.domain

/**
 * Created by lif on 2017/7/6.
 */

interface Command<T> {

    fun execute(): T
}