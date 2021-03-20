package com.github.overlhaverde.deliverycalculator.data.remote.services

import com.github.overlhaverde.deliverycalculator.data.remote.model.Response
import com.github.overlhaverde.deliverycalculator.data.remote.model.UserRemote
import retrofit2.http.GET

interface UserService {
    @GET("user")
    suspend fun getUser(): Response<UserRemote>
}