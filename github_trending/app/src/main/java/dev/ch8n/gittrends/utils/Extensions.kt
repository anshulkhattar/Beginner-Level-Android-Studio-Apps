package dev.ch8n.gittrends.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import dev.ch8n.gittrends.ui.base.BaseActivity
import jp.wasabeef.picasso.transformations.CropCircleTransformation

fun AppCompatImageView.loadImage(src: String) = Picasso.get()
    .load(src)
    .resize(120, 120)
    .centerCrop()
    .into(this)

fun String.toToast(context: Context) = Toast
    .makeText(context, this, Toast.LENGTH_SHORT)
    .show()

fun String.logError(tag: String = "GitTrendsApp") = Log.e(tag, this)

fun View.setVisibility(isVisibe: Boolean) = if (isVisibe) {
    this.setVisibility(View.VISIBLE)
} else {
    this.setVisibility(View.GONE)
}

fun AppCompatImageView.loadAvatar(src: String) = Picasso.get()
    .load(src)
    .resize(120, 120)
    .transform(CropCircleTransformation())
    .into(this)

inline fun <reified T : AppCompatActivity> BaseActivity.launchActivity(bundle: Bundle = Bundle()) {
    startActivity(Intent(this as Context, T::class.java).also {
        it.putExtras(bundle)
    })

}

