package com.github.overlhaverde.deliverycalculator.data.remote.mapper.googleapi

import com.github.overlhaverde.deliverycalculator.data.remote.mapper.DataRemoteMapper
import com.github.overlhaverde.deliverycalculator.data.remote.model.distancematrixresponse.DistanceMatrixResponse
import com.github.overlhaverde.deliverycalculator.domain.model.distance.Distance
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.domain.model.distance.Duration

object DistanceMatrixMapper: DataRemoteMapper<DistanceMatrixResponse, DistanceSearch>() {
    override fun toDomain(data: DistanceMatrixResponse) = DistanceSearch(
        destination = data.destination_addresses[0],
        origin = data.origin_addresses[0],
        distance = Distance(
            text = data.rows[0].elements[0].distance.text,
            value = data.rows[0].elements[0].distance.value,
        ),
        duration = Duration(
            text = data.rows[0].elements[0].duration.text,
            value = data.rows[0].elements[0].duration.value,
        )
    )
}