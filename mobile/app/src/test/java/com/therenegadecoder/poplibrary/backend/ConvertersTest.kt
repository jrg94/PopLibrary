package com.therenegadecoder.poplibrary.backend

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ConvertersTest {

    @Test
    fun fromTimestamp() {
        assertEquals(Date(1000), Converters().fromTimestamp(1000))
    }

    @Test
    fun fromDate() {
        assertEquals(1000L, Converters().fromDate(Date(1000)))
    }

    @Test
    fun fromLexileLevel() {
    }

    @Test
    fun fromLexileText() {
    }

    @Test
    fun fromFountasAndPinell() {
    }

    @Test
    fun fromFountasAndPinellText() {
    }
}