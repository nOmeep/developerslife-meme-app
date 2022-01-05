package com.example.meme_developers_life_app.util

import android.view.View

abstract class DoubleClickListener : View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(view: View?) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(view!!)
            lastClickTime = 0
        }

        lastClickTime = clickTime
    }

    abstract fun onDoubleClick(v: View)

    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA = 300
    }

}