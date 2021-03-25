package com.github.overlhaverde.deliverycalculator.data.repository

import com.github.overlhaverde.deliverycalculator.data.datasource.local.SharedPreferencesDataSource
import com.github.overlhaverde.deliverycalculator.domain.repository.ConfigurationsRepository

class ConfigurationsRepositoryImpl(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
): ConfigurationsRepository {

    override fun getDarkMode() = sharedPreferencesDataSource.getDarkMode()

    override fun setDarkMode(enabled: Boolean) = sharedPreferencesDataSource.setDarkMode(enabled)

    override fun getDistanceSearchData() = sharedPreferencesDataSource.getDistanceSearchData()

    override fun setOrigin(data: String) = sharedPreferencesDataSource.setOrigin(data)

    override fun setDestination(data: String) = sharedPreferencesDataSource.setDestination(data)

    override fun setKmPrice(data: String) = sharedPreferencesDataSource.setKmPrice(data)

    override fun setRoundDistance(data: Boolean) = sharedPreferencesDataSource.setRoundDistance(data)

}