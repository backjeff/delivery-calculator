package com.github.overlhaverde.deliverycalculator.di

import androidx.fragment.app.Fragment
import com.github.overlhaverde.deliverycalculator.feature.splashscreen.SplashScreenActions
import com.github.overlhaverde.deliverycalculator.navigation.actions.SplashScreenActionsImpl
import org.koin.dsl.module

val navigationModule = module {

    factory<SplashScreenActions> { (fragment: Fragment) ->
        SplashScreenActionsImpl(fragment)
    }

}
