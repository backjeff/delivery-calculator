package com.github.overlhaverde.deliverycalculator.domain.repository

import com.github.overlhaverde.deliverycalculator.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
}