package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val Listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>(){

    private val allNotes = ArrayList<Notes>()

    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textView: TextView = itemView.findViewById(R.id.text)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        //converts XML file Into ItemView all this is done by View Holder
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_view, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            Listener.onItemCLicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes = allNotes[position]
        holder.textView.text = currentNotes.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun update(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun onItemCLicked(notes: Notes)
}
