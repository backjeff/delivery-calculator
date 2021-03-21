package com.github.ovelhaverde.deliverycalculator.distancesearch

import android.app.Application
import androidx.lifecycle.asLiveData
import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.GetDistanceUseCase
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.feature.base.BaseViewModel
import com.github.overlhaverde.deliverycalculator.feature.core.ViewState
import com.github.overlhaverde.deliverycalculator.feature.core.ViewState.Neutral
import com.github.overlhaverde.deliverycalculator.feature.extensions.getString
import com.github.overlhaverde.deliverycalculator.feature.extensions.postError
import com.github.overlhaverde.deliverycalculator.feature.extensions.postSuccess
import com.github.overlhaverde.deliverycalculator.feature.extensions.useCase
import com.github.overlhaverde.deliverycalculator.feature.util.MoneyMask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class DistanceSearchViewModel(
    application: Application
): BaseViewModel(application), KoinComponent {

    private val getDistanceUseCase: GetDistanceUseCase by useCase()
    private val formatDistanceResponseUseCase: FormatDistanceResponseUseCase by useCase()

    private val _distanceSearch = MutableStateFlow<ViewState<DistanceSearch>>(Neutral)
    val distanceSearch = _distanceSearch.asStateFlow().asLiveData()

    private val _price = MutableStateFlow<ViewState<FormatDistanceResponseUseCase.Response>>(Neutral)
    val price = _price.asStateFlow().asLiveData()

    fun getDistance(
        origin: String,
        destination: String,
    ) {
        getDistanceUseCase(
            params = GetDistanceUseCase.Params(
                origins = origin,
                destinations = destination
            ),
            onSuccess = { _distanceSearch.postSuccess(it) },
            onError = { _distanceSearch.postError(it) }
        )
    }

    fun getPriceFormatted(
        kmPrice: String,
        distanceSearch: DistanceSearch
    ) {
        formatDistanceResponseUseCase(
            params = FormatDistanceResponseUseCase.Params(
                kmPrice = formatKmPrice(kmPrice),
                distanceSearch = distanceSearch
            ),
            onSuccess = { _price.postSuccess(it) },
            onError = { _price.postError(it) }
        )
    }

    private fun formatKmPrice(kmPrice: String) = runCatching { MoneyMask.unmask(kmPrice).toDouble() }.getOrNull()
}