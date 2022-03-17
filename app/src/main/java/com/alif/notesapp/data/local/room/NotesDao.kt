package com.alif.notesapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alif.notesapp.data.local.Notes

@Dao
interface NotesDao {
    @Insert
    suspend fun addNote(note: Notes)

    @Query("SELECT * FROM tb_notes ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Notes>>
}