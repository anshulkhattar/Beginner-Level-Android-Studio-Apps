package dev.ch8n.gittrends.data.local.db.dao

import androidx.room.*
import dev.ch8n.gittrends.data.model.db.*

@Dao
interface ProjectDao {

    //######## Add/Update ##########

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProject(project: Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProjects(projects: List<Project>)

    //######## Select ##########

    @Query("SELECT * FROM Project")
    suspend fun getProjects(): List<Project>

    @Query("SELECT * FROM Project WHERE gitUserName=:userName")
    suspend fun getProject(userName: String): Project

    //######## Delete ##########

    @Query("DELETE FROM Project")
    suspend fun clearProjects()

    @Query("DELETE FROM Project WHERE gitUserName=:userName")
    suspend fun deleteProject(userName: String)


}