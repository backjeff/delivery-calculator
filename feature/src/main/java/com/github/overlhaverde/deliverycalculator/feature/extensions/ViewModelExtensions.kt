package com.github.overlhaverde.deliverycalculator.feature.extensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.UseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent = inject<U> {
    parametersOf(viewModelScope)
}

fun AndroidViewModel.getString(resId: Int): String = getApplication<Application>().getString(resId)