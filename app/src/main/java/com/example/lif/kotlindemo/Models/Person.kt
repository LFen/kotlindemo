package com.example.lif.kotlindemo.Models

import android.widget.Toast
import com.example.lif.kotlindemo.activities.MainActivity

/**
 * Created by lif on 2017/7/5.
 */

class Person {
    var name: String =""
        get() = field.toUpperCase()
        set(value) {
            field = "Name: $value"
        }

    var surname: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Surname: $value"
        }
}
