package com.github.overlhaverde.deliverycalculator.data.remote.model.distancematrixresponse

import com.google.gson.annotations.SerializedName

data class DistanceMatrixResponse(
    @SerializedName("destination_addresses") val destination_addresses: List<String>,
    @SerializedName("origin_addresses") val origin_addresses: List<String>,
    @SerializedName("rows") val rows: List<Row>,
    @SerializedName("status") val status: String
)