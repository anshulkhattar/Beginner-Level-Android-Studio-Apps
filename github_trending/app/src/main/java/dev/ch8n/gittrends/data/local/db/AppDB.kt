package dev.ch8n.gittrends.data.local.db

import dev.ch8n.gittrends.data.local.db.dao.ProfileDao
import dev.ch8n.gittrends.data.local.db.dao.ProjectDao

class AppDB(private val database: RoomDB) :
    DatabaseProvider {

    override val profileDao: ProfileDao by lazy {
        database.profileDao()
    }

    override val projectDao: ProjectDao by lazy {
        database.projectDao()
    }
}