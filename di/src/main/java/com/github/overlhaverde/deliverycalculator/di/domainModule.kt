package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.domain.interactor.*
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode.GetDarkModeUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.darkmode.SetDarkModeUseCase
import com.github.overlhaverde.deliverycalculator.domain.interactor.distancesearchform.*
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single {
        CoroutineContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetDistanceUseCase(
            scope = scope,
            contextProvider = get(),
            distanceRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        FormatDistanceResponseUseCase(
            scope = scope,
            contextProvider = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetDarkModeUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetDarkModeUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetDistanceSearchFormDataUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetDestinationUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetOriginUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetKmPriceUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetRoundDistanceUseCase(
            scope = scope,
            contextProvider = get(),
            configurationsRepository = get()
        )
    }

}
