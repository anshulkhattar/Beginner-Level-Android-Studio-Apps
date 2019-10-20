package dev.ch8n.gittrends.data.others.repos


import dev.ch8n.gittrends.data.others.sources.dao.TrendingItemsDao
import dev.ch8n.gittrends.data.others.sources.entities.CachedTrendingItem
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CacheTrendingRepoistory @Inject constructor(
    private val trendingItemsDao: TrendingItemsDao
) : CacheTrendingRepo {

    override suspend fun putTrendingItems(itemList: List<TrendingItem>): Result<Unit, Exception> =
        withContext(Dispatchers.IO) {
            val cacheItems = itemList.map { item ->
                return@map CachedTrendingItem(
                    item.gitProfileName,
                    item.avatar,
                    item.gitProfileUrl,
                    item.username,
                    item.projectDesc,
                    item.projectName,
                    item.projectUrl
                )
            }
            Result.build { trendingItemsDao.putTrendingItems(cacheItems) }
        }


    override suspend fun deleteTrendingItems(): Result<Int, Exception> =
        withContext(Dispatchers.IO) {
            Result.build { trendingItemsDao.deleteAllItems() }
        }

    override suspend fun getTrendingItems(): Result<List<TrendingItem>, Exception> =
        withContext(Dispatchers.IO) {
            Result.build {
                trendingItemsDao.getTrendingItems().map { cache ->
                    return@map TrendingItem(
                        cache.avatar,
                        cache.gitProfileName,
                        cache.gitProfileUrl,
                        cache.username,
                        cache.projectDesc,
                        cache.projectName,
                        cache.projectUrl
                    )
                }
            }
        }

}

