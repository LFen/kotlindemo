package com.example.lif.kotlindemo.activities

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by lif on 2017/7/5.
 */

open class BaseActivity: AppCompatActivity() {

    fun showToast(message: String, long: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, long).show()
    }

    fun showToast(strResId: Int, long: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, strResId, long).show()
    }
}
