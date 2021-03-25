package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.data.repository.ConfigurationsRepositoryImpl
import com.github.overlhaverde.deliverycalculator.data.repository.DistanceRepositoryImpl
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository
import com.github.overlhaverde.deliverycalculator.domain.repository.DistanceRepository
import org.koin.dsl.module

val dataModule = module {

    single<DistanceRepository> { DistanceRepositoryImpl(get()) }
    single<ConfigurationsRepository> { ConfigurationsRepositoryImpl(get()) }

}

