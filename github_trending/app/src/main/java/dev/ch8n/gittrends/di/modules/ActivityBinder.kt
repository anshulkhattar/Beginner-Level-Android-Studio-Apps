package dev.ch8n.gittrends.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.ch8n.gittrends.ui.gitPreview.PreviewActivity
import dev.ch8n.gittrends.ui.home.MainActivity
import dev.ch8n.gittrends.ui.home.di.MainDI

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector(modules = [MainDI::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindPreviewActivity(): PreviewActivity

}