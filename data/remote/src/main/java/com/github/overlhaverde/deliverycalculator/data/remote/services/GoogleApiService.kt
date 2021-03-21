package com.github.overlhaverde.deliverycalculator.data.remote.services

import com.github.overlhaverde.deliverycalculator.data.remote.model.distancematrixresponse.DistanceMatrixResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {
    @GET("distancematrix/json")
    suspend fun getDistanceMatrix(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
        @Query("language") language: String, //pt-BR
        @Query("key") key: String,
    ): DistanceMatrixResponse
}