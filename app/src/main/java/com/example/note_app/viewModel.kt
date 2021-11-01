package com.example.note_app

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class viewModel(context:Application): AndroidViewModel(context) {
    private val repository: NoteRepository
    private val notes: LiveData<List<NotesData>>

    init {
        val noteDao = NoteDataBase.getInstance(context).NoteDao()
        repository = NoteRepository(noteDao)
        notes = repository.getNotes
    }

    fun getNotes(): LiveData<List<NotesData>> {
        return notes
    }

    fun addNote(noteText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(NotesData(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(noteID, noteText)
        }
    }

    fun deleteNote(noteID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(noteID)
        }
    }
}