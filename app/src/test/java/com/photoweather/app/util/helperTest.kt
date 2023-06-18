package com.photoweather.app.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class helperTest {

    @Test
    fun whenDegreeIsNullReturnFalse() {
        val result=helper.checkCardDataIsValid(degree = null, city = "alexandria", location = "city center")
        assertThat(result).isFalse()
    }
    @Test
    fun whenCityIsNullReturnFalse() {
        val result=helper.checkCardDataIsValid(degree = "30", city = null, location = "city center")
        assertThat(result).isFalse()
    }
    @Test
    fun whenLocationIsNullReturnFalse() {
        val result=helper.checkCardDataIsValid(degree = null, city = "alexandria", location = null)
        assertThat(result).isFalse()
    }
    @Test
    fun whenDataIsValidReturnTrue() {
        val result=helper.checkCardDataIsValid(degree = "30", city = "alexandria", location = "city center")
        assertThat(result).isTrue()
    }
}