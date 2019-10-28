package com.tani.avengersassemble

import android.graphics.Bitmap

class Global{

    companion object chosen{

        var chosen_pic : Bitmap? = null
        fun returnImg() : Bitmap{
            return chosen_pic!!
        }
    }

}