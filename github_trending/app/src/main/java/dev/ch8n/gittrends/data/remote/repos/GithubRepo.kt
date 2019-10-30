package dev.ch8n.gittrends.data.remote.repos

import dev.ch8n.gittrends.data.model.local.list.TrendingItem


interface GithubRepo {

    suspend fun getTrendingProjects(language: String): List<TrendingItem>
}