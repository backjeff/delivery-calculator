package com.github.overlhaverde.deliverycalculator.navigation.actions

import androidx.fragment.app.Fragment
import com.github.ovelhaverde.deliverycalculator.navigation.R
import com.github.overlhaverde.deliverycalculator.feature.splashscreen.SplashFragmentDirections
import com.github.overlhaverde.deliverycalculator.feature.splashscreen.SplashScreenActions
import com.github.overlhaverde.deliverycalculator.navigation.extensions.navigate

class SplashScreenActionsImpl(
    private val fragment: Fragment
): SplashScreenActions {
    override fun goToDistanceSearch() = fragment.navigate(
        SplashFragmentDirections.actionSplashFragmentToDistanceSearchFragment()
    )
}