package com.anil.notes24.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

class Converters {
    val gson = Gson()

    @TypeConverter
    fun fromTimeStamp(date: Long?): Date?{
        return date?.let{ Date(it) }
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long?{
        return date?.time
    }
}