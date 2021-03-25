package com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope

class GetDarkModeUseCase(
    scope: CoroutineScope,
    contextProvider: CoroutineContextProvider,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Boolean, Unit>(scope, contextProvider) {

    override fun run(params: Unit?) = configurationsRepository.getDarkMode()

}
