package com.github.overlhaverde.deliverycalculator.data.remote.model

import com.google.gson.annotations.SerializedName

data class Response<T>(

    @SerializedName("message")
    val message: List<MessageResponse>,

    @SerializedName("data")
    val data: T?,

    @SerializedName("error")
    val fault: ErrorResponse? = null,

    @SerializedName("token")
    val token: String? = null

)

data class ErrorResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("message") val message: String? = null
)

data class MessageResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("message") val message: String? = null
)
