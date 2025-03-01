package com.example.androidcomposedemo

import android.app.Application
import com.rc.base.koinModule.networkModule
import com.rc.facebook.ui.screen.facebookModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MyApplication)
            modules(
                facebookModule,
                networkModule
            )
        }
    }
}