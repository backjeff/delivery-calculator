package com.github.overlhaverde.deliverycalculator.domain.extensions

import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test
import java.util.*

internal class DomainExtensionsKtTest {

    @Before
    fun setup() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun `GIVEN a Double WHEN calculatePrice with round = false THEN values MUST match`() {
        1000.0.calculatePrice(100.0, false) shouldBe "$1.00"
        1001.0.calculatePrice(100.0, false) shouldBe "$1.00"
        1010.0.calculatePrice(100.0, false) shouldBe "$1.01"
        1100.0.calculatePrice(100.0, false) shouldBe "$1.10"
        1490.0.calculatePrice(100.0, false) shouldBe "$1.49"
        1499.0.calculatePrice(100.0, false) shouldBe "$1.50"
        1500.0.calculatePrice(100.0, false) shouldBe "$1.50"
        1501.0.calculatePrice(100.0, false) shouldBe "$1.50"
        1510.0.calculatePrice(100.0, false) shouldBe "$1.51"
        1990.0.calculatePrice(100.0, false) shouldBe "$1.99"
    }

    @Test
    fun `GIVEN a Double WHEN calculatePrice with round = true THEN values MUST match`() {
        1000.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1001.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1010.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1100.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1490.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1499.0.calculatePrice(100.0, true) shouldBe "$1.00"
        1500.0.calculatePrice(100.0, true) shouldBe "$2.00"
        1501.0.calculatePrice(100.0, true) shouldBe "$2.00"
        1510.0.calculatePrice(100.0, true) shouldBe "$2.00"
        1990.0.calculatePrice(100.0, true) shouldBe "$2.00"
    }

}
