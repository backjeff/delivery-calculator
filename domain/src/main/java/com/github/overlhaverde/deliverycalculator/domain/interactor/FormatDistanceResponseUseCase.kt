package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase.*
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import java.security.InvalidParameterException
import java.text.NumberFormat

class FormatDistanceResponseUseCase(
    scope: CoroutineScope
) : UseCase<Response, Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw InvalidParameterException()
        params.kmPrice == null -> throw Exception("Invalid value per km")
        else -> flow {
            emit(
                Response(
                    distance = params.distanceSearch.distance.text,
                    value = getPrice(params, params.kmPrice)
                )
            )
        }
    }

    private fun getPrice(
        params: Params,
        kmPrice: Double
    ) = NumberFormat.getCurrencyInstance().run {
        maximumFractionDigits = 2
        format(
            (params.distanceSearch.distance.value / 1000) * (kmPrice/100)
        )
    }

    data class Params(
        val kmPrice: Double?,
        val distanceSearch: DistanceSearch
    )

    data class Response(
        val distance: String,
        val value: String,
    )

}
