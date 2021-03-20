package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.UserDataSource
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapper
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapperImpl
import com.github.overlhaverde.deliverycalculator.data.remote.core.WebServiceFactory
import com.github.overlhaverde.deliverycalculator.data.remote.datasource.UserDataSourceImpl
import com.github.overlhaverde.deliverycalculator.data.remote.services.UserService
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {

    single {
        WebServiceFactory.createWebService(
            type = UserService::class.java,
            url = "https://private-6b1e50-backjeffbees.apiary-mock.com/"
        )
    } bind UserService::class

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single { UserDataSourceImpl(get(), get()) } bind UserDataSource::class

}