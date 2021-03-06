package com.therenegadecoder.poplibrary.backend

import com.therenegadecoder.poplibrary.data.FountasAndPinell
import com.therenegadecoder.poplibrary.data.Lexile
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
    fun fromLexileLevelNA() {
        assertEquals("NA1000", Converters().fromLexileLevel(Lexile(1000)))
    }

    @Test
    fun fromLexileLevelAD() {
        assertEquals("AD1000", Converters().fromLexileLevel(Lexile(1000, Lexile.LexileType.AD)))
    }

    @Test
    fun fromLexileTextNA() {
        assertEquals(Lexile(1000), Converters().fromLexileText("NA1000"))
    }

    @Test
    fun fromLexileTextBR() {
        assertEquals(Lexile(400, Lexile.LexileType.BR), Converters().fromLexileText("BR400"))
    }

    @Test
    fun fromFountasAndPinell() {
        assertEquals("A", Converters().fromFountasAndPinell(FountasAndPinell.A))
    }

    @Test
    fun fromFountasAndPinellText() {
        assertEquals(FountasAndPinell.B, Converters().fromFountasAndPinellText("B"))
    }
}