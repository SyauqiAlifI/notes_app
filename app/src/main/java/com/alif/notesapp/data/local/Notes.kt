package com.alif.notesapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_notes")
@Parcelize
data class Notes(
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val priority: Priority
) : Parcelable