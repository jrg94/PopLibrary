package com.example.poplibrary

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?) : Long? {
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
    fun fromFountasAndPinellText(fountasAndPinellText: String?) : FountasAndPinell? {
        return fountasAndPinellText?.let { FountasAndPinell.valueOf(it) }
    }
}