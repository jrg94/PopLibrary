package com.example.poplibrary

import androidx.room.TypeConverter
import java.util.*

public class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?) : Long? {
        return date?.time?.toLong()
    }
}