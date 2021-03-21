package com.github.overlhaverde.deliverycalculator.feature.extensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import com.github.overlhaverde.deliverycalculator.feature.core.ViewState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent = inject<U> {
    parametersOf(viewModelScope)
}

fun AndroidViewModel.getString(resId: Int): String = getApplication<Application>().getString(resId)