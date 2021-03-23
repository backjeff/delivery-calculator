package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class SetDestinationUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Unit, SetDestinationUseCase.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        else -> configurationsRepository.setDestination(params.data)
    }

    data class Params(
        val data: String
    )

}
