package com.therenegadecoder.poplibrary.backend

import androidx.room.TypeConverter
import com.therenegadecoder.poplibrary.data.FountasAndPinell
import com.therenegadecoder.poplibrary.data.Lexile
import java.util.*

/**
 * A class of converters used by the Room database to
 * convert objects like Date, Lexile, and FountasAndPinell
 * into sqlite cells.
 *
 * @author Jeremy Grifski
 */
class Converters {

    /**
     * Converts a timestamp to a Date object.
     *
     * @param value the long to be converted
     * @return the timestamp [value] as a date
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converts a Date object into a long.
     *
     * @param date the date object
     * @return the [date] as a timestamp
     */
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    /**
     * Converts a Lexile object to a Lexile string.
     * i.e. Lexile(200, LexileType.BR) -> BR200
     * Not to be confused with [Lexile.toString].
     *
     * @param lexileLevel the Lexile level object (i.e. Lexile(1000))
     * @return the [lexileLevel] as a Lexel string (i.e. NA1000)
     */
    @TypeConverter
    fun fromLexileLevel(lexileLevel: Lexile?): String? {
        return lexileLevel?.let {
            it.type.name + it.level.toString()
        }
    }

    /**
     * Converts a Lexile string to a Lexile object.
     * i.e. NA100 -> Lexile(100, LexileType.NA)
     *
     * @param lexileText the lexile as a string (i.e. AD1600)
     * @return the [lexileText] as a Lexile object (i.e. Lexile(1700))
     */
    @TypeConverter
    fun fromLexileText(lexileText: String?): Lexile? {
        return lexileText?.let {
            Lexile(
                it.substring(2).toInt(),
                Lexile.LexileType.valueOf(it.substring(0, 2))
            )
        }
    }

    /**
     * Converts a FountasAndPinell object to a string.
     *
     * @param fountasAndPinell an F&P rating object
     * @return the [fountasAndPinell] rating as a string
     */
    @TypeConverter
    fun fromFountasAndPinell(fountasAndPinell: FountasAndPinell?): String? {
        return fountasAndPinell?.name
    }

    /**
     * Converts a F&P rating string to an FountasAndPinell object.
     *
     * @param fountasAndPinellText an F&P rating string
     * @return the [fountasAndPinellText] string as a FountasAndPinell object
     */
    @TypeConverter
    fun fromFountasAndPinellText(fountasAndPinellText: String?): FountasAndPinell? {
        return fountasAndPinellText?.let { FountasAndPinell.valueOf(it) }
    }
}