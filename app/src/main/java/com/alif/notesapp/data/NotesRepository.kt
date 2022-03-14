package com.alif.notesapp.data

import android.provider.ContactsContract
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }
}
