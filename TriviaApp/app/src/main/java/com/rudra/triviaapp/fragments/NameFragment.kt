package com.rudra.triviaapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.rudra.trivia.fragments.SingleChoiceFragment
import com.rudra.triviaapp.R
import com.rudra.triviaapp.activities.TriviaListActivity

class NameFragment : Fragment() {

    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_name, container, false)
        val nextButton = mView.findViewById(R.id.nextButton) as Button
        val historyButton = mView.findViewById(R.id.historyButton) as Button
        val nameEditText = mView.findViewById(R.id.nameEditText) as TextInputEditText

        nextButton.setOnClickListener { view ->
            if (nameEditText.text?.isEmpty() != true) {
                loadFragment(SingleChoiceFragment(), nameEditText.text.toString())
            } else {
                nameEditText.error = "Please enter name"
            }
        }

        historyButton.setOnClickListener { view ->
            val intent = Intent (activity, TriviaListActivity::class.java)
            activity?.startActivity(intent)
        }

        return mView
    }

    private fun loadFragment(fragment: Fragment, name: String){
        val bundle = Bundle()
        bundle.putString("name", name)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}