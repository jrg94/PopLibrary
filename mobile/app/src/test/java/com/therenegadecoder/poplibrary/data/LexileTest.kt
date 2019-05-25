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
    fun toStringAD() {
        val lex = Lexile(1200, Lexile.LexileType.AD)
        assertEquals("AD1200L", lex.toString())
    }

    @Test
    fun toStringNA() {
        val lex = Lexile(194, Lexile.LexileType.NA)
        assertEquals("194L", lex.toString())
    }

    @Test
    fun compareToPositive() {
        val lex1 = Lexile(270, Lexile.LexileType.NA)
        val lex2 = Lexile(400, Lexile.LexileType.NA)
        assertTrue(lex1 < lex2)
    }

    @Test
    fun compareToNegative() {
        val lex1 = Lexile(270, Lexile.LexileType.NA)
        val lex2 = Lexile(400, Lexile.LexileType.BR)
        assertTrue(lex2 < lex1)
    }
}