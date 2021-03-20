package com.github.overlhaverde.deliverycalculator.data.remote.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceFactory {

    inline fun <reified T> createWebService(
        type: Class<T>,
        url: String = ""
    ): T {
        return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(type)
    }

}


