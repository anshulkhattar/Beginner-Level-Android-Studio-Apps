package com.example.notes

import androidx.lifecycle.LiveData

class NotesRepo(private val NotesDao: NotesDao) {
    val getAllNotes : LiveData<List<Notes>> = NotesDao.getAllNotes()

    suspend fun insert(notes: Notes) {
        NotesDao.insert(notes)
    }
    suspend fun delete(notes: Notes){
        NotesDao.delete(notes)
    }
}