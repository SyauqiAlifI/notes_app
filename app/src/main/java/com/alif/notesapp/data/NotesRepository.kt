package com.alif.notesapp.data

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }
}
