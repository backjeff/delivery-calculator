package com.github.overlhaverde.deliverycalculator.data.datasource.local.datasource

import com.github.overlhaverde.deliverycalculator.data.datasource.local.SharedPreferencesDataSource
import com.github.overlhaverde.deliverycalculator.data.datasource.local.service.PreferencesHelper
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearchFormData
import kotlinx.coroutines.flow.flow

class SharedPreferencesDataSourceImpl(
    private val preferencesHelper: PreferencesHelper
) : SharedPreferencesDataSource {

    override fun getDarkMode() = flow {
        emit(
            preferencesHelper.getBoolean("dark_mode")
        )
    }

    override fun setDarkMode(enabled: Boolean) = flow {
        emit(
            preferencesHelper.saveBoolean("dark_mode", enabled)
        )
    }

    override fun getDistanceSearchData() = flow {
        emit(
            DistanceSearchFormData(
                origin = preferencesHelper.getString("origin") ?: "",
                destination = preferencesHelper.getString("destination") ?: "",
                kmPrice = preferencesHelper.getString("kmprice") ?: "",
                roundDistance = preferencesHelper.getBoolean("rounddistance"),
            )
        )
    }

    override fun setOrigin(data: String) = flow {
        emit(
            preferencesHelper.saveString("origin", data)
        )
    }

    override fun setDestination(data: String) = flow {
        emit(
            preferencesHelper.saveString("destination", data)
        )
    }

    override fun setKmPrice(data: String) = flow {
        emit(
            preferencesHelper.saveString("kmprice", data)
        )
    }

    override fun setRoundDistance(data: Boolean) = flow {
        emit(
            preferencesHelper.saveBoolean("rounddistance", data)
        )
    }

}