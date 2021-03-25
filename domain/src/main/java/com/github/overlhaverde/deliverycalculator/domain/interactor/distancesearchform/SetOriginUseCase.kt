package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class SetOriginUseCase(
    scope: CoroutineScope,
    contextProvider: CoroutineContextProvider,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Unit, SetOriginUseCase.Params>(scope, contextProvider) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        else -> configurationsRepository.setOrigin(params.data)
    }

    data class Params(
        val data: String
    )

}
