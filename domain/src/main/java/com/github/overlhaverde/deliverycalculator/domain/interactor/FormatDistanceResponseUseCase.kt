package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase.Params
import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase.Response
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.security.InvalidParameterException
import java.text.NumberFormat
import kotlin.math.roundToInt

class FormatDistanceResponseUseCase(
    scope: CoroutineScope,
    private val configurationsRepository: ConfigurationsRepository
) : UseCase<Response, Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        params.kmPrice == null -> throw Exception("Invalid value per km")
        else -> flow {
            emit(
                Response(
                    distance = params.distanceSearch.distance.text,
                    duration = params.distanceSearch.duration.text,
                    value = calculatePrice(
                        distance = params.distanceSearch.distance.value.toDouble(),
                        kmPrice = params.kmPrice
                    )
                )
            )
        }
    }

    private suspend fun calculatePrice(
        distance: Double, // meters
        kmPrice: Double // cents
    ) = NumberFormat.getCurrencyInstance().run {
        maximumFractionDigits = 2
        format(
            getProperRoundedValue(distance) * (kmPrice / 100)
        )
    }

    private suspend fun getProperRoundedValue(value: Double): Double {
        val v = (value / 1000)
        return if (configurationsRepository.getDistanceSearchData().first().roundDistance) {
            v.roundToInt().toDouble()
        } else {
            v
        }
    }

    data class Params(
        val kmPrice: Double?,
        val distanceSearch: DistanceSearch
    )

    data class Response(
        val distance: String,
        val duration: String,
        val value: String,
    )

}
