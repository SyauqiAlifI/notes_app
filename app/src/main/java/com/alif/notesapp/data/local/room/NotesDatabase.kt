package com.alif.notesapp.data.local.room

import androidx.room.Database
import com.alif.notesapp.data.local.Notes

@Database(entities = [Notes::class], version = 1)
class NotesDatabase {
}