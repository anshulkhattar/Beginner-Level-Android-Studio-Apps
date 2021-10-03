package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val getAllNotes:LiveData<List<Notes>>
    private val repo : NotesRepo
    init{
        val dataBase = NotesDataBase.getDatabase(application).getNotesDao()
        repo = NotesRepo(dataBase)
        getAllNotes =repo.getAllNotes
    }

    //since our IO operation are running in background thread so we have to use (viewModelScope.launch)
    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(notes)
    }

    fun insertNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(notes)
    }

}