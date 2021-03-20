package com.github.overlhaverde.deliverycalculator.data.datasource.remote

import com.github.overlhaverde.deliverycalculator.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getUser(): Flow<User>
}