package com.github.overlhaverde.deliverycalculator.data.remote.mapper

import com.github.overlhaverde.deliverycalculator.data.remote.model.UserRemote
import com.github.overlhaverde.deliverycalculator.domain.model.User

object UserMapper: DataRemoteMapper<UserRemote, User>() {
    override fun toDomain(data: UserRemote) = User(
        id = data.id ?: "",
        accountId = null,
        name = data.name ?: ""
    )
}