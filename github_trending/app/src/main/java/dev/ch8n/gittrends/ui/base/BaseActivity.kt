package dev.ch8n.gittrends.ui.base

import android.content.Context
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper


abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        setup()
    }

    abstract fun setup()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(base))
    }


}
