package com.github.overlhaverde.deliverycalculator.feature.extensions

import com.github.ovelhaverde.deliverycalculator.styles.util.MoneyMask

fun String.removeCurrencyMask() = runCatching { (MoneyMask.unmask(this)) }.getOrNull()