package com.rudra.triviaapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rudra.triviaapp.fragments.NameFragment
import com.rudra.triviaapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, NameFragment())
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}