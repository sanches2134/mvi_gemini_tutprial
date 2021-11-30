package com.dating.mvigeminitutorial

import android.app.Application
import com.dating.mvigeminitutorial.di.geminiModule
import com.dating.mvigeminitutorial.di.networkModule
import com.dating.mvigeminitutorial.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoryModule, geminiModule)
        }
    }
}
