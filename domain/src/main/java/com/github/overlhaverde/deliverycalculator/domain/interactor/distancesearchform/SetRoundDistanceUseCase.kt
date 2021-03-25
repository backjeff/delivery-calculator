package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class SetRoundDistanceUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Unit, SetRoundDistanceUseCase.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        else -> configurationsRepository.setRoundDistance(params.data)
    }

    data class Params(
        val data: Boolean
    )

}
