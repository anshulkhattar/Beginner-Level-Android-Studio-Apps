package dev.ch8n.gittrends.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class GitUser(
    @PrimaryKey
    var username: String,
    var avatar: String,
    var gitProfileName: String,
    var gitProfileUrl: String
)