package dev.ch8n.gittrends.data.model.remote


import com.google.gson.annotations.SerializedName

data class GitRepo(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)