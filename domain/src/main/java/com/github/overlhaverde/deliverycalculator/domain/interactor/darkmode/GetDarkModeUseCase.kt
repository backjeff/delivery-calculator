package com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope

class GetDarkModeUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Boolean, Unit>(scope) {

    override fun run(params: Unit?) = configurationsRepository.getDarkMode()

}
