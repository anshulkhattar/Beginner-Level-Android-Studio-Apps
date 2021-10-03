package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),INotesRVAdapter {

    lateinit var viewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.notesRecycle)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter  //this will be our adapter for app


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.getAllNotes.observe(this, Observer {list ->
            list?.let {
                adapter.update(it)
            }
        })
    }

    override fun onItemCLicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: android.view.View) {
        val editText = findViewById<EditText>(R.id.enterText)
        val noteText = editText.text.toString().trim()
        if(noteText.isNotEmpty()){
            viewModel.insertNotes(Notes(noteText))
            Toast.makeText(this,"Note Added Successfully",Toast.LENGTH_SHORT).show()
            editText.text.clear()
        }

    }

}