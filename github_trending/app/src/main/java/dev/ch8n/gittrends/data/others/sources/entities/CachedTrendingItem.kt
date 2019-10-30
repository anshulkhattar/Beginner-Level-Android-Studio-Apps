package dev.ch8n.gittrends.data.others.sources.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GithubTrending")
data class CachedTrendingItem(
    @PrimaryKey
    val gitProfileName: String,
    val avatar: String,
    val gitProfileUrl: String,
    val username: String,
    val projectDesc: String,
    val projectName: String,
    val projectUrl: String
)