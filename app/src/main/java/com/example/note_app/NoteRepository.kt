package com.example.note_app

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val getNotes: LiveData<List<NotesData>> = noteDao.getAllNote()

    suspend fun addNote(note: NotesData){
        noteDao.addNote(note)
    }

    suspend fun updateNote(id: Int,note :String){
        noteDao.updateNote(id,note)

    }

    suspend fun deleteNote(id: Int){
        noteDao.deleteNote(id)
    }
}