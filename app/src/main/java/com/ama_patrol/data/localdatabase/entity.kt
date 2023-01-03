package com.ama_patrol.data.localdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

import java.text.DateFormat

@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timeStamp: Long = System.currentTimeMillis(),
    val longitude: Double,
    val latitude: Double,
    val created: Long = System.currentTimeMillis(),
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}

class InvalidNoteException(message: String) : Exception(message)