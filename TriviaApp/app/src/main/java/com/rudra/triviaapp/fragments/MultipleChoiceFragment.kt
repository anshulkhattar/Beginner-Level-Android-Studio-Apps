package com.rudra.triviaapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rudra.triviaapp.R

class MultipleChoiceFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_multiple_choice, container, false)
        val nextButton = mView.findViewById(R.id.nextButton) as Button
        val checkbox1 = mView.findViewById(R.id.checkbox1) as CheckBox
        val checkbox2 = mView.findViewById(R.id.checkbox2) as CheckBox
        val checkbox3 = mView.findViewById(R.id.checkbox3) as CheckBox
        val checkbox4 = mView.findViewById(R.id.checkbox4) as CheckBox

        val args = arguments
        val name = args?.getString("name")
        val answer1 = args?.getString("answer1")

        nextButton.setOnClickListener {view ->
            val checkBoxString =
                mutableListOf<String>()
            val nameString =
                arrayOf<CheckBox>(checkbox1, checkbox2, checkbox3, checkbox4)
            for (i in 0..3) {
                //looping to see which of the checkboxes are selected
                if (nameString[i].isChecked) {
                    checkBoxString.add(nameString[i].text.toString())
                }
            }
            checkBoxString.joinToString(prefix = " ", postfix = " ",separator = " , ", transform = { it -> it.toString() }) // using ' , ' as separator
            val finalString: String = checkBoxString.toString().removeSurrounding("[", "]") // removing brackets
            if (answer1 != null && name != null) {
                if (finalString.isNotBlank()) {
                    loadFragment(SummaryFragment(), name, answer1, finalString)
                } else {
                    Toast.makeText(context, "Please select an item", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return mView
    }

    private fun loadFragment(
        fragment: Fragment,
        name: String?,
        answer1: String?,
        checkBoxString: String
    ){
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("answer1", answer1)
        bundle.putString("answer2", checkBoxString)
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        private const val TAG = "MultipleChoiceFragment"
    }
}