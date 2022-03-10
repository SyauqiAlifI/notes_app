package com.alif.notesapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.alif.notesapp.data.local.Notes

@Dao
interface NotesDao {
    @Insert
    fun addNote(note: Notes) {

    }
}