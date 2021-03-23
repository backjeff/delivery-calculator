package com.github.overlhaverde.deliverycalculator.feature.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.getColor(@ColorRes colorId: Int) = ContextCompat.getColor(this.context, colorId)