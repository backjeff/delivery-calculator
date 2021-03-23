package com.github.overlhaverde.deliverycalculator.domain.model.distance

data class DistanceSearchFormData(
    val origin: String,
    val destination: String,
    val kmPrice: String,
)