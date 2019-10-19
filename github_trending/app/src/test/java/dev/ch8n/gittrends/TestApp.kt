package dev.ch8n.gittrends

import android.app.Application
import com.squareup.picasso.Picasso


class TestApp : GitTrendApp() {

    override fun onCreate() {
        super.onCreate()
        val picasso = Picasso.Builder(this).build()
        Picasso.setSingletonInstance(picasso)
    }
}