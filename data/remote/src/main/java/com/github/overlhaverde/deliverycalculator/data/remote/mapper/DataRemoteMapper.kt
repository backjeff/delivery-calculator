package com.github.overlhaverde.deliverycalculator.data.remote.mapper

abstract class DataRemoteMapper<in R, out D> {
    abstract fun toDomain(data: R): D
}