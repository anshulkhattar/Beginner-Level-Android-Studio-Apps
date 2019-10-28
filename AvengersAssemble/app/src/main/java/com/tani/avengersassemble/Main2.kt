package com.tani.avengersassemble

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = intent

        val name = intent.getStringExtra("avengername")



        textView.text = name

        val cwg =Global.chosen
        //ERROR??
        val picFinal = cwg.chosen_pic
        imageView.setImageBitmap(picFinal)



    }
}
