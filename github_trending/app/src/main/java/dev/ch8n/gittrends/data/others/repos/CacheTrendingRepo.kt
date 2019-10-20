package dev.ch8n.gittrends.data.others.repos

import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.utils.Result

interface CacheTrendingRepo {

    suspend fun getTrendingItems(): Result<List<TrendingItem>, Exception>

    suspend fun putTrendingItems(itemList: List<TrendingItem>): Result<Unit, Exception>

    suspend fun deleteTrendingItems(): Result<Int, Exception>

}