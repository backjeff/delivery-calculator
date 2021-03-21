package com.github.overlhaverde.deliverycalculator.di

import com.github.ovelhaverde.deliverycalculator.distancesearch.DistanceSearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    viewModel { DistanceSearchViewModel(androidApplication()) }

}
