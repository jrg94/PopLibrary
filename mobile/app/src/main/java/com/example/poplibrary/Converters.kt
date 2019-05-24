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
        return lexileLevel?.toString()
    }

    @TypeConverter
    fun fromLexileInteger(lexileText: String?): Lexile? {
        return lexileText?.let {
            Lexile(
                it.substring(2, it.length - 1).toInt(),
                Lexile.LexileType.valueOf(it.substring(0, 2))
            )
        }
    }
}