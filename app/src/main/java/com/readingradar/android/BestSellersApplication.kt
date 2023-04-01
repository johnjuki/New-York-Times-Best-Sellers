package com.readingradar.android

import android.app.Application
import android.content.Context

class BestSellersApplication : Application() {
    init {
        app = this
    }

    companion object {
        private lateinit var app: BestSellersApplication
        fun getAppContext(): Context = app.applicationContext
    }

}
