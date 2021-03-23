package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.data.datasource.local.SharedPreferencesDataSource
import com.github.overlhaverde.deliverycalculator.data.datasource.local.service.PreferencesHelper
import com.github.overlhaverde.deliverycalculator.data.datasource.local.datasource.SharedPreferencesDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataLocalModule = module {

    single { PreferencesHelper(androidApplication()) }

    single<SharedPreferencesDataSource> { SharedPreferencesDataSourceImpl(get()) }

}