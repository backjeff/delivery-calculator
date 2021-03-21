package com.github.overlhaverde.deliverycalculator

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.github.overlhaverde.deliverycalculator.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppApplication : Application() {

    val a: ViewBinding? = null

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    dataRemoteModule,
                    navigationModule,
                    featureModule
                )
            ).androidContext(applicationContext)
        }

        defineTheme()
    }

    private fun defineTheme() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}