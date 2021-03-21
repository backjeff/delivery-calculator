package com.github.overlhaverde.deliverycalculator.data.remote.model.distancematrixresponse

import com.google.gson.annotations.SerializedName

data class Duration(
    @SerializedName("text") val text: String,
    @SerializedName("value") val value: Int
)