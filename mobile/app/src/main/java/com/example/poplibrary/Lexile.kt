package com.example.poplibrary

/**
 * A lexile is a reading level unit. It's often defined as a number between
 * -400 and +2000. However, negative values are typically written as BR400L
 * for beginning reader -400.
 */
class Lexile (val level: Int, val type: LexileType) : Comparable<Lexile> {

    /**
     * A lexile classification enum.
     */
    enum class LexileType(val text: String) {
        AD("Adult-directed"),
        NC("Non-conforming"),
        HL("High-low"),
        IG("Illustrated guide"),
        GN("Graphic novel"),
        BR("Beginning reader"),
        NP("Non-prose")
    }

    /**
     * Converts the lexile level to an integer for comparison purposes.
     */
    fun toInteger() = when {
        this.type == LexileType.BR -> -level
        else -> level
    }

    override fun toString(): String {
        return type.name + level.toString() + "L"
    }

    override fun compareTo(other: Lexile): Int {
        return this.toInteger() - other.toInteger()
    }
}

