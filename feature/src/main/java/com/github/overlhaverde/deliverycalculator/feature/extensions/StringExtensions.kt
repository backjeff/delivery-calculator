package com.github.overlhaverde.deliverycalculator.feature.extensions

import com.github.overlhaverde.deliverycalculator.feature.util.MoneyMask

fun String.removeCurrencyMask() = runCatching { (MoneyMask.unmask(this)) }.getOrNull()