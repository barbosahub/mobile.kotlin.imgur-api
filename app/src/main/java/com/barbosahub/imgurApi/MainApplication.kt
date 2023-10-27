package com.barbosahub.imgurApi

import android.app.Application
import com.barbosahub.imgurApi.di.StartKoin
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.core.context.stopKoin
class MainApplication : Application() {
    var appcontext:android.content.Context? = null
    override fun onCreate() {
        super.onCreate()
        appcontext = this
        StartKoin.initKoin(this)
        Fresco.initialize(this)
    }
    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
    companion object{
        const val BASE_URL = "https://api.imgur.com/3/gallery/"
    }
}