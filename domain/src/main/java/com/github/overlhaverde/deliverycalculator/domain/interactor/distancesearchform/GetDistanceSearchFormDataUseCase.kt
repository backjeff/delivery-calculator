package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearchFormData
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope

class GetDistanceSearchFormDataUseCase(
    scope: CoroutineScope,
    contextProvider: CoroutineContextProvider,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<DistanceSearchFormData, Unit>(scope, contextProvider) {

    override fun run(params: Unit?) = configurationsRepository.getDistanceSearchData()

}
