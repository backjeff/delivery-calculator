package com.github.overlhaverde.deliverycalculator.domain.extensions

import java.text.NumberFormat
import kotlin.math.roundToInt

fun Double.calculatePrice(
    kmPriceInCents: Double, // cents
    shouldRoundDistance: Boolean
) = NumberFormat.getCurrencyInstance().run {
    maximumFractionDigits = 2
    format(
        (this@calculatePrice/1000).roundIfNeeded(shouldRoundDistance) * (kmPriceInCents/100)
    )
}

fun Double.roundIfNeeded(round: Boolean) =
    if (round) {
        this.roundToInt().toDouble()
    } else {
        this
    }