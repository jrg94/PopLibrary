package com.therenegadecoder.poplibrary.data

import org.junit.Test

import org.junit.Assert.*

class LexileTest {

    @Test
    fun toStringBR() {
        val lex = Lexile(300, Lexile.LexileType.BR)
        assertEquals("BR300L", lex.toString())
    }

    @Test
    fun compareTo() {
    }
}