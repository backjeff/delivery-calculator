package com.github.overlhaverde.deliverycalculator.di

import com.github.overlhaverde.deliverycalculator.data.repository.UserRepositoryImpl
import com.github.overlhaverde.deliverycalculator.domain.repository.UserRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    single { UserRepositoryImpl(get()) } bind UserRepository::class

}

