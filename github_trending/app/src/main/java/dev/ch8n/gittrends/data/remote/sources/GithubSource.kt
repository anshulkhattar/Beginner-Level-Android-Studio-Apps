package dev.ch8n.gittrends.data.remote.sources

import dev.ch8n.gittrends.data.model.remote.TrendingResponse
import dev.ch8n.gittrends.data.remote.config.ApiManager
import org.intellij.lang.annotations.Language
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubSource @Inject constructor(private val apiManager: ApiManager) {



    suspend fun getTrendingRepos(language: String):List<TrendingResponse> = apiManager
        .githubService
        .getTrendingRepos(language = language)

}