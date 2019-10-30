package dev.ch8n.gittrends.di.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dev.ch8n.gittrends.GitTrendApp
import dev.ch8n.gittrends.data.local.db.AppDB
import dev.ch8n.gittrends.data.local.db.DatabaseProvider
import dev.ch8n.gittrends.data.local.db.ROOM_DB_NAME
import dev.ch8n.gittrends.data.local.db.RoomDB
import dev.ch8n.gittrends.data.local.prefs.AppPrefs
import dev.ch8n.gittrends.data.local.prefs.PreferenceProvider
import javax.inject.Singleton

@Module
class DataBaseBinder {

    @Provides
    fun provideRoomClient(app: GitTrendApp) =
        Room.databaseBuilder(app.applicationContext, RoomDB::class.java,
            ROOM_DB_NAME
        )
            .fallbackToDestructiveMigration()

    @Provides
    @Singleton
    fun provideGitTrendDB(roomClient: RoomDatabase.Builder<RoomDB>): RoomDB =
        roomClient.build()

    @Provides
    @Singleton
    fun provideApplicationDatabase(database: RoomDB): DatabaseProvider =
        AppDB(database)

    @Provides
    @Singleton
    fun provideApplicationPrefs(app: GitTrendApp): PreferenceProvider =
        AppPrefs(app.applicationContext)


}