package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(applicationModules)
        }
    }
}
