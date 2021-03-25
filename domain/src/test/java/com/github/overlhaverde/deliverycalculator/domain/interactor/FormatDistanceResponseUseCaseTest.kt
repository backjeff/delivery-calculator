package com.github.overlhaverde.deliverycalculator.domain.interactor

import com.github.overlhaverde.deliverycalculator.domain.model.distance.Distance
import com.github.overlhaverde.deliverycalculator.domain.model.distance.DistanceSearch
import com.github.overlhaverde.deliverycalculator.domain.model.distance.Duration
import com.github.overlhaverde.deliverycalculator.domain.testFlow
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.core.error.MissingPropertyException
import java.security.InvalidParameterException
import java.util.*

internal class FormatDistanceResponseUseCaseTest {

    @Before
    fun setup() {
        Locale.setDefault(Locale.US)
    }

    @Test(expected = InvalidParameterException::class)
    fun `GIVEN useCase WHEN params are null THEN throw InvalidParameterException`() {
        // GIVEN
        val useCase = getUseCase()

        // WHEN
        useCase.run(params = null)

        // THEN
        // expected InvalidParameterException
    }

    @Test(expected = MissingPropertyException::class)
    fun `GIVEN useCase WHEN kmPrice params is null THEN throw MissingPropertyException`() {
        // GIVEN
        val useCase = getUseCase()

        // WHEN
        useCase.run(
            params = FormatDistanceResponseUseCase.Params(
                kmPrice = null,
                distanceSearch = mockk()
            )
        )

        // THEN
        // expected MissingPropertyException
    }

    @Test
    fun `GIVEN useCase WHEN its called THEN return flow with defined values`() {
        // GIVEN
        val useCase = getUseCase()

        // WHEN - THEN
        useCase.run(
            params = FormatDistanceResponseUseCase.Params(
                kmPrice = 100.0,
                distanceSearch = getDistanceSearch()
            )
        ).testFlow {
            value shouldBe "$1.00"
            distance shouldBe "distance text"
            duration shouldBe "duration text"
        }
    }

    private fun getDistanceSearch(
        distance: Int = 1000,
        duration: Int = 1000,
    ) = DistanceSearch(
        origin = "origin",
        destination = "destination",
        distance = Distance(
            text = "distance text",
            value = distance
        ),
        duration = Duration(
            text = "duration text",
            value = duration
        )
    )

    private fun getUseCase() = FormatDistanceResponseUseCase(
        scope = mockk(),
        contextProvider = mockk()
    )

}