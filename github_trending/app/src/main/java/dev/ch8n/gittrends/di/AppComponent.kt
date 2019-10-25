package dev.ch8n.gittrends.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.ch8n.gittrends.GitTrendApp
import dev.ch8n.gittrends.di.modules.ActivityBinder
import dev.ch8n.gittrends.di.modules.DataBaseBinder
import dev.ch8n.gittrends.di.modules.NetworkBinder
import javax.inject.Singleton


@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        NetworkBinder::class,
        ActivityBinder::class,
        DataBaseBinder::class
    )
)
interface AppComponent : AndroidInjector<GitTrendApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GitTrendApp): Builder

        fun build(): AppComponent
    }
}