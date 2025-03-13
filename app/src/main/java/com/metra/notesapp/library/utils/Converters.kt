package com.metra.notesapp.library.utils

import androidx.room.TypeConverter
import java.util.UUID

class Converters {

    @TypeConverter
    fun toUUID(stringUUID: String) = UUID.fromString(stringUUID)

    @TypeConverter
    fun fromUUID(uuid: UUID) = uuid.toString()
}
