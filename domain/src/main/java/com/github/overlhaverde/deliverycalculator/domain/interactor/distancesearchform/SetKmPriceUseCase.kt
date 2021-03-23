package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class SetKmPriceUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Unit, SetKmPriceUseCase.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        else -> configurationsRepository.setKmPrice(params.data)
    }

    data class Params(
        val data: String
    )

}
