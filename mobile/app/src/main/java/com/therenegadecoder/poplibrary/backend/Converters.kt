package com.therenegadecoder.poplibrary.backend

import androidx.room.TypeConverter
import com.therenegadecoder.poplibrary.data.FountasAndPinell
import com.therenegadecoder.poplibrary.data.Lexile
import java.util.*

/**
 * A class of converters used by the Room database to
 * convert objects like Date, Lexile, and FountasAndPinell
 * into sqlite cells.
 */
class Converters {

    /**
     * Converts a timestamp to a Date object.
     *
     * @param value the long to be converted
     * @return the date
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converts a Date object into a long.
     */
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromLexileLevel(lexileLevel: Lexile?): String? {
        return lexileLevel?.let {
            it.type.name + it.level.toString()
        }
    }

    @TypeConverter
    fun fromLexileText(lexileText: String?): Lexile? {
        return lexileText?.let {
            Lexile(
                it.substring(2).toInt(),
                Lexile.LexileType.valueOf(it.substring(0, 2))
            )
        }
    }

    @TypeConverter
    fun fromFountasAndPinell(fountasAndPinell: FountasAndPinell?): String? {
        return fountasAndPinell?.name
    }

    @TypeConverter
    fun fromFountasAndPinellText(fountasAndPinellText: String?): FountasAndPinell? {
        return fountasAndPinellText?.let { FountasAndPinell.valueOf(it) }
    }
}