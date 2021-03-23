package com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform

import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearchFormData
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope

class GetDistanceSearchFormDataUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<DistanceSearchFormData, Unit>(scope) {

    override fun run(params: Unit?) = configurationsRepository.getDistanceSearchData()

}
