package dev.ch8n.gittrends.data.others.sources.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ch8n.gittrends.data.others.sources.entities.CachedTrendingItem

@Dao
interface TrendingItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putTrendingItems(listItems: List<CachedTrendingItem>)

    @Query("SELECT * FROM GithubTrending")
    suspend fun getTrendingItems(): List<CachedTrendingItem>

    @Query("DELETE FROM GithubTrending")
    suspend fun deleteAllItems(): Int

}