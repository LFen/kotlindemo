package com.example.lif.kotlindemo.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.lif.kotlindemo.App
import com.example.lif.kotlindemo.R
import com.example.lif.kotlindemo.extensions.ctx
import com.example.lif.kotlindemo.extensions.slideEnter
import com.example.lif.kotlindemo.extensions.slideExit
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

/**
 * Created by lif on 2017/8/3.
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {toolbar.title = value}

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun attachScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply {
        progress = 1f
    }
}