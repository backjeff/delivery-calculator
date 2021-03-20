package com.github.overlhaverde.deliverycalculator.di

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

}
