package dev.ch8n.gittrends.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Project(
    @PrimaryKey
    var gitUserName: String,
    var projectDesc: String,
    var projectName: String,
    var projectUrl: String
)