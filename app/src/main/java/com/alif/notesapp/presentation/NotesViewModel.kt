package com.alif.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alif.notesapp.data.NotesRepository
import com.alif.notesapp.data.local.Notes
import com.alif.notesapp.data.local.room.NotesDao
import com.alif.notesapp.data.local.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao : NotesDao = NotesDatabase.getDatabase(application).notesDao()
    private val notesRepository = NotesRepository(notesDao)

    val sortByHighPriority: LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority: LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(note)
        }
    }

    fun searchNotesByQuery(query: String) : LiveData<List<Notes>> {
        return notesRepository.searchNotesByQuery(query)
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun updateNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }
}