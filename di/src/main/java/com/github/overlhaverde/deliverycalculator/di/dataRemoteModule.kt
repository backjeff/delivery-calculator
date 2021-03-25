package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.GoogleApiDataSource
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapper
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapperImpl
import com.github.overlhaverde.deliverycalculator.data.remote.core.WebServiceFactory
import com.github.overlhaverde.deliverycalculator.data.remote.datasource.GoogleApiDataSourceImpl
import com.github.overlhaverde.deliverycalculator.data.remote.services.GoogleApiService
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single<GoogleApiService> {
        WebServiceFactory.createWebService(
            type = GoogleApiService::class.java,
            url = "https://maps.googleapis.com/maps/api/"
        )
    }

    single<GoogleApiDataSource> { GoogleApiDataSourceImpl(get(), get()) }

}