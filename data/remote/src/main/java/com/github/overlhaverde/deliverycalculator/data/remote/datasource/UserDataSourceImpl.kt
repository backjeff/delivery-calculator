package com.github.overlhaverde.deliverycalculator.data.remote.datasource

import com.github.overlhaverde.deliverycalculator.data.datasource.remote.UserDataSource
import com.github.overlhaverde.deliverycalculator.data.remote.core.RequestWrapper
import com.github.overlhaverde.deliverycalculator.data.remote.mapper.UserMapper
import com.github.overlhaverde.deliverycalculator.data.remote.services.UserService
import kotlinx.coroutines.flow.flow

class UserDataSourceImpl(
    private val requestWrapper: RequestWrapper,
    private val userService: UserService
): UserDataSource {
    override fun getUser() = flow {
        emit(
            UserMapper.toDomain(
                requestWrapper.wrapperResponse {
                    userService.getUser()
                }.data!!
            )
        )
    }
}