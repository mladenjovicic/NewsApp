package com.mladenjovicic.newsapp

import android.app.Application

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NewsApplication
    }
}