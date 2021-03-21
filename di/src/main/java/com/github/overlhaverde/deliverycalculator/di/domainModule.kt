package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.domain.interactor.FormatDistanceResponseUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.GetDistanceUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.GetUser
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single {
        CoroutineContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetUser(
            scope = scope,
            userRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetDistanceUseCase(
            scope = scope,
            distanceRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        FormatDistanceResponseUseCase(
            scope = scope
        )
    }

}
