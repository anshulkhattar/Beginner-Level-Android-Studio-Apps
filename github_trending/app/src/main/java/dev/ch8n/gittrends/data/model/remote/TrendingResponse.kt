package dev.ch8n.gittrends.data.model.remote


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("repo")
    val gitRepo: GitRepo,
    @SerializedName("url")
    val url: String,
    @SerializedName("username")
    val username: String
)