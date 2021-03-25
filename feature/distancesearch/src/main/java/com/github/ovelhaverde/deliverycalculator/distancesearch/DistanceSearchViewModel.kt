package com.github.ovelhaverde.deliverycalculator.distancesearch

import android.app.Application
import androidx.lifecycle.asLiveData
import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.GetDistanceUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode.GetDarkModeUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode.SetDarkModeUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform.*
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearchFormData
import com.github.overlhaverde.deliverycalculator.feature.base.BaseViewModel
import com.github.overlhaverde.deliverycalculator.feature.core.ViewState
import com.github.overlhaverde.deliverycalculator.feature.core.ViewState.Neutral
import com.github.overlhaverde.deliverycalculator.feature.extensions.postError
import com.github.overlhaverde.deliverycalculator.feature.extensions.postSuccess
import com.github.overlhaverde.deliverycalculator.feature.extensions.removeCurrencyMask
import com.github.overlhaverde.deliverycalculator.feature.extensions.useCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class DistanceSearchViewModel(
    application: Application
): BaseViewModel(application), KoinComponent {

    private val getDistanceUseCase: GetDistanceUseCase by useCase()
    private val formatDistanceResponseUseCase: FormatDistanceResponseUseCase by useCase()
    private val getDarkModeUseCase: GetDarkModeUseCase by useCase()
    private val setDarkModeUseCase: SetDarkModeUseCase by useCase()
    private val getDistanceSearchFormDataUseCase: GetDistanceSearchFormDataUseCase by useCase()
    private val setOriginUseCase: SetOriginUseCase by useCase()
    private val setDestinationUseCase: SetDestinationUseCase by useCase()
    private val setKmPriceUseCase: SetKmPriceUseCase by useCase()
    private val setRoundDistanceUseCase: SetRoundDistanceUseCase by useCase()

    private val _distanceSearch = MutableStateFlow<ViewState<DistanceSearch>>(Neutral)
    val distanceSearch = _distanceSearch.asStateFlow().asLiveData()

    private val _price = MutableStateFlow<ViewState<FormatDistanceResponseUseCase.Response>>(Neutral)
    val price = _price.asStateFlow().asLiveData()

    private val _darkMode = MutableStateFlow<ViewState<Boolean>>(Neutral)
    val darkMode = _darkMode.asStateFlow().asLiveData()

    private val _distanceSearchFormData = MutableStateFlow<ViewState<DistanceSearchFormData>>(Neutral)
    val distanceSearchFormData = _distanceSearchFormData.asStateFlow().asLiveData()

    init {
        getDarkModeUseCase(
            onSuccess = { _darkMode.postSuccess(it) }
        )
        getDistanceSearchFormDataUseCase(
            onSuccess = { _distanceSearchFormData.postSuccess(it) }
        )
    }

    fun setDarkMode(enabled: Boolean) {
        setDarkModeUseCase(
            params = SetDarkModeUseCase.Params(enabled),
            onSuccess = { _darkMode.postSuccess(enabled) }
        )
    }

    fun setOrigin(data: String) {
        setOriginUseCase(
            params = SetOriginUseCase.Params(data)
        )
    }

    fun setDestination(data: String) {
        setDestinationUseCase(
            params = SetDestinationUseCase.Params(data)
        )
    }

    fun setKmPrice(data: String) {
        data.removeCurrencyMask()?.let {
            setKmPriceUseCase(
                params = SetKmPriceUseCase.Params(it)
            )
        }
    }

    fun setRoundDistance(data: Boolean) {
        setRoundDistanceUseCase(
            params = SetRoundDistanceUseCase.Params(data)
        )
    }

    fun getPrice(
        origin: String,
        destination: String,
        kmPrice: String,
        shouldRoundDistance: Boolean = false,
    ) {
        getDistanceUseCase(
            params = GetDistanceUseCase.Params(
                origins = origin,
                destinations = destination
            ),
            onSuccess = {
                getPriceFormatted(
                    kmPrice = kmPrice,
                    distanceSearch = it,
                    shouldRoundDistance = shouldRoundDistance
                )
            },
            onError = { _distanceSearch.postError(it) }
        )
    }

    private fun getPriceFormatted(
        kmPrice: String,
        distanceSearch: DistanceSearch,
        shouldRoundDistance: Boolean,
    ) {
        formatDistanceResponseUseCase(
            params = FormatDistanceResponseUseCase.Params(
                kmPrice = kmPrice.removeCurrencyMask()?.toDouble(),
                distanceSearch = distanceSearch,
                shouldRoundDistance = shouldRoundDistance
            ),
            onSuccess = { _price.postSuccess(it) },
            onError = { _price.postError(it) }
        )
    }

}