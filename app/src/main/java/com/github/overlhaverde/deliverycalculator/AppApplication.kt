package com.github.overlhaverde.deliverycalculator

import android.app.Application
import com.github.overlhaverde.deliverycalculator.di.dataModule
import com.github.overlhaverde.deliverycalculator.di.dataRemoteModule
import com.github.overlhaverde.deliverycalculator.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    dataRemoteModule
                )
            ).androidContext(applicationContext)
        }

    }
}