package dev.ch8n.gittrends.data.remote.repos

import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.data.remote.sources.GithubSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GithubRepository @Inject constructor(private val githubSource: GithubSource) :
    GithubRepo {

    override suspend fun getTrendingProjects(language: String): List<TrendingItem> =
        withContext(Dispatchers.IO) {
            githubSource.getTrendingRepos(language).map { trendingResponse ->
                return@map TrendingItem(
                    trendingResponse.avatar,
                    trendingResponse.name,
                    trendingResponse.url,
                    trendingResponse.username,
                    trendingResponse.gitRepo.description,
                    trendingResponse.gitRepo.name,
                    trendingResponse.gitRepo.url
                )
            }
        }


}