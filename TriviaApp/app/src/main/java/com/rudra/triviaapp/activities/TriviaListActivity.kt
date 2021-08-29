package com.rudra.triviaapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.rudra.triviaapp.adapters.ListAdapter
import com.rudra.triviaapp.db.DatabaseHandler
import com.rudra.triviaapp.R
import com.rudra.triviaapp.models.Trivia

class TriviaListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_list)

        val recyclerView: ListView = findViewById(R.id.recyclerView)

        val databaseHandler = DatabaseHandler(this)
        val list: List<Trivia> = databaseHandler.viewData()
        val id = Array(list.size){"0"}
        val name = Array(list.size){"null"}
        val a1 = Array(list.size){"null"}
        val a2 = Array(list.size){"null"}
        val time = Array(list.size){"null"}

        for((index, e) in list.withIndex()){
            id[index] = e.id.toString()
            name[index] = e.name
            a1[index] = e.a1
            a2[index] = e.a2
            time[index] = e.time
        }
        //creating custom ArrayAdapter
        val myListAdapter = ListAdapter(this, id, name, a1, a2, time)
        recyclerView.adapter = myListAdapter
    }
}