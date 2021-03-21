package com.github.overlhaverde.deliverycalculator.data.remote.model.distancematrixresponse

import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("elements") val elements: List<Element>
)