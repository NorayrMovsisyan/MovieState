package com.example.moviestate.app

import android.app.Application
import com.example.moviestate.app.di.appModule
import com.example.moviestate.core.api.di.apiModule
import com.example.moviestate.movies.di.moviesModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(moviesModule, appModule, apiModule)
        }
    }
}