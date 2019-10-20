package dev.ch8n.gittrends.data.local.db

import dev.ch8n.gittrends.data.local.db.dao.ProfileDao
import dev.ch8n.gittrends.data.local.db.dao.ProjectDao

interface DatabaseProvider {

    val projectDao: ProjectDao

    val profileDao: ProfileDao

}