package com.rudra.triviaapp.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.rudra.triviaapp.R

class ListAdapter (private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val a1: Array<String>, private val a2: Array<String>, private val time: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val nameTextView = rowView.findViewById(R.id.nameTextView) as TextView
        val answer1TextView = rowView.findViewById(R.id.answer1TextView) as TextView
        val answer2TextView = rowView.findViewById(R.id.answer2TextView) as TextView
        val timeTextView = rowView.findViewById(R.id.timeTextView) as TextView

        nameTextView.text = name[position]
        answer1TextView.text = a1[position]
        answer2TextView.text = a2[position]
        timeTextView.text = time[position]
        return rowView
    }
}