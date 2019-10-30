package dev.ch8n.gittrends.data.remote.sources

import dev.ch8n.gittrends.data.model.remote.TrendingResponse
import dev.ch8n.gittrends.data.remote.config.AppAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    //https://github-trending-api.now.sh/developers?language=java&since=weekly
    @GET(AppAPI.GET_TRENDING_REPO)
    suspend fun getTrendingRepos(
        @Query("language") language: String = "java",
        @Query("since") since: String = "weekly"
    ): List<TrendingResponse>
}