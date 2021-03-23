package com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import java.security.InvalidParameterException

class SetDarkModeUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Unit, SetDarkModeUseCase.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        else -> configurationsRepository.setDarkMode(params.enabled)
    }

    data class Params(
        val enabled: Boolean
    )

}
