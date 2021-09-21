package com.rudra.trivia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.rudra.triviaapp.R
import com.rudra.triviaapp.fragments.MultipleChoiceFragment

class SingleChoiceFragment : Fragment() {
    lateinit var mView: View
    lateinit var radioButton: RadioButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_single_choice, container, false)
        val nextButton = mView.findViewById(R.id.nextButton) as Button
        val radioGroup = mView.findViewById(R.id.radioGroup) as RadioGroup

        val args = arguments
        val name = args?.getString("name")

        nextButton.setOnClickListener {view ->
            if (radioGroup.checkedRadioButtonId == -1)
            {
                // no radio buttons are checked
                Toast.makeText(context, "Please select a option", Toast.LENGTH_SHORT).show()
            }
            else
            {
                // one of the radio buttons is checked
                val selectedOption: Int = radioGroup.checkedRadioButtonId
                radioButton = mView.findViewById(selectedOption)
                loadFragment(MultipleChoiceFragment(), radioButton.text.toString(), name.toString())
            }

        }
        return mView
    }

    private fun loadFragment(fragment: Fragment, answer1: String, name: String){
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("answer1", answer1)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}