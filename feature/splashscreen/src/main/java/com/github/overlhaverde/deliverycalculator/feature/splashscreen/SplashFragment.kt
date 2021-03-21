package com.github.overlhaverde.deliverycalculator.feature.splashscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.ovelhaverde.deliverycalculator.feature.splashscreen.R
import com.github.overlhaverde.deliverycalculator.domain.interactor.core.CoroutineContextProvider
import com.github.overlhaverde.deliverycalculator.feature.extensions.actions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val coroutineContextProvider: CoroutineContextProvider by inject()
    private val splashScreenActions: SplashScreenActions by actions()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(coroutineContextProvider.main).launch {
            delay(2000)
            splashScreenActions.goToDistanceSearch()
        }

    }

}