package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.User
import com.github.overlhaverde.deliverycalculator.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope

class GetUser(
    scope: CoroutineScope,
    private val userRepository: UserRepository
): UseCase<User, Unit>(scope) {
    override fun run(params: Unit?) = userRepository.getUser()
}