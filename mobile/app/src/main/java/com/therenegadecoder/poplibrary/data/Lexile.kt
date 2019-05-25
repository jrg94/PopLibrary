package com.therenegadecoder.poplibrary.data

/**
 * A lexile is a reading level unit. It's often defined as a number between
 * -400 and +2000. However, negative values are typically written as BR400L
 * for beginning reader -400.
 */
class Lexile(val level: Int, val type: LexileType) : Comparable<Lexile> {

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
        NP("Non-prose"),
        NA("Not Applicable")
    }

    /**
     * Converts the lexile level to an integer for comparison purposes.
     *
     * @return the lexile level as an integer (rough range: -400 - +2000)
     */
    private fun toInteger() = when {
        this.type == LexileType.BR -> -level
        else -> level
    }

    /**
     * Overrides the toString() method to provide an easy way to
     * print the lexile level to the user. In this case, the
     * output will look like: {type}{level}L (i.e. BR300L).
     *
     * @return a string representing this lexile
     */
    override fun toString(): String {
        return when (this.type) {
            LexileType.NA -> level.toString() + "L"
            else -> type.name + level.toString() + "L"
        }
    }

    /**
     * A helpful operator for comparing lexile levels.
     *
     * @return an integer > 0 when `this` lexile is larger than `other`
     */
    override fun compareTo(other: Lexile): Int {
        return this.toInteger() - other.toInteger()
    }
}

