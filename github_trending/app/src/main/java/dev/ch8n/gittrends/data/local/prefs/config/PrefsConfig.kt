package dev.ch8n.gittrends.data.local.prefs.config

import android.content.Context
import android.content.SharedPreferences

open class PrefsConfig constructor(context: Context,prefFile:String) {

    protected var prefs:SharedPreferences = context.applicationContext.getSharedPreferences(prefFile,Context.MODE_PRIVATE)
        private set

}