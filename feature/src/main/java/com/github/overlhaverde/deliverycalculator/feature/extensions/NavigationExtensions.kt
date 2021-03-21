package com.github.overlhaverde.deliverycalculator.feature.extensions

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <F : Fragment, reified V : Any> F.actions() = inject<V> {
    parametersOf(this)
}