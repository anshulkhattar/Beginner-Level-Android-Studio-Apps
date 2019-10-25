package dev.ch8n.gittrends

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.ch8n.gittrends.di.DaggerAppComponent
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

open class GitTrendApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        initCalligraphy()
    }

    private fun initCalligraphy() = ViewPump.init(
        ViewPump.builder()
            .addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            )
            .build()
    )


}