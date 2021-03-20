package com.github.overlhaverde.deliverycalculator.data.repository

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.UserDataSource
import com.github.overlhaverde.deliverycalculator.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
): UserRepository {
    override fun getUser() = userDataSource.getUser()
}