package com.metra.notesapp.library.utils

import androidx.room.TypeConverter
import java.util.UUID

/**
 * Functions will be called automatically by Room at runtime when saving/loading [UUID] values
 */
class Converters {

    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun toUUID(uuid: String): UUID = UUID.fromString(uuid)
}
