package com.rudra.triviaapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.rudra.triviaapp.R
import com.rudra.triviaapp.activities.MainActivity
import com.rudra.triviaapp.db.DatabaseHandler
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SummaryFragment : Fragment() {

    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_summary, container, false)
        val finishButton = mView.findViewById(R.id.finishButton) as Button
        val nameTextView = mView.findViewById(R.id.nameTextView) as TextView
        val timeTextView = mView.findViewById(R.id.timeTextView) as TextView
        val a1TextView = mView.findViewById(R.id.a1TextView) as TextView
        val a2TextView = mView.findViewById(R.id.a2TextView) as TextView

        val databaseHandler = DatabaseHandler(context)
        val args = arguments
        val name = args?.getString("name")
        val answer1 = args?.getString("answer1")
        val answer2 = args?.getString("answer2")

        val sdf = SimpleDateFormat("dd/MM hh:mm aa")
        val currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date())

        a1TextView.text = answer1
        a2TextView.text = answer2
        nameTextView.text = "Hello $name"
        timeTextView.text = "Submission Time: $currentDate"

        finishButton.setOnClickListener {
            val status = databaseHandler.addTst (answer1.toString(), answer2.toString(), name.toString(), currentDate)
            if (status > -1) {
                Toast.makeText(context,"Answers saved!", Toast.LENGTH_SHORT).show()
                val intent = Intent (activity, MainActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            } else {
                Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()
            }

        }
        return mView
    }

}