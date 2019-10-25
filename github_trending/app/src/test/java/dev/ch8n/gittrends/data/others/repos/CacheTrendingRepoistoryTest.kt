package dev.ch8n.gittrends.data.others.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import dev.ch8n.gittrends.MainCoroutineRule
import dev.ch8n.gittrends.data.local.db.RoomDB
import dev.ch8n.gittrends.data.others.sources.dao.TrendingItemsDao
import dev.ch8n.gittrends.di.modules.DataBaseBinder
import dev.ch8n.gittrends.utils.Result
import dev.ch8n.gittrends.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class CacheTrendingRepoistoryTest {


    /**
     * todo:
     * test repository provided dao is working
     * 1. insert into repo tranforms the trendingtype to cacheType and store in DB
     * 2. delete from repo deletes data from database
     * 3. get from repos converts the data from cacheType to trendingItem type
     */

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RoomDB
    private lateinit var repo: CacheTrendingRepoistory
    private lateinit var dao: TrendingItemsDao

    @Before
    fun setup() {

        val datbaseBinder = DataBaseBinder()

        database = datbaseBinder.provideGitTrendDB(
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                RoomDB::class.java
            ).allowMainThreadQueries()
        )

        dao = database.trendingItemDao()
        repo = CacheTrendingRepoistory(dao)

    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun `insert LocalTrending Items to DB`() = runBlocking {
        repo.putTrendingItems(Utils.getSampleTrendingData())
        Truth.assertThat(dao.getTrendingItems().size).isEqualTo(3)
    }

    @Test
    fun `check if insert is tranformed correctly`() = runBlocking {
        val repoData = Utils.getSampleTrendingData()
        repo.putTrendingItems(repoData)
        val daoData = dao.getTrendingItems()
        for (i in repoData.indices) {
            Truth.assertThat(daoData[i].avatar).isEqualTo(repoData[i].avatar)
            Truth.assertThat(daoData[i].gitProfileName).isEqualTo(repoData[i].gitProfileName)
            Truth.assertThat(daoData[i].gitProfileUrl).isEqualTo(repoData[i].gitProfileUrl)
            Truth.assertThat(daoData[i].username).isEqualTo(repoData[i].username)
            Truth.assertThat(daoData[i].projectDesc).isEqualTo(repoData[i].projectDesc)
            Truth.assertThat(daoData[i].projectName).isEqualTo(repoData[i].projectName)
        }
    }

    @Test
    fun `delete LocalTrending Items from DB`() = runBlocking {
        repo.deleteTrendingItems()
        Truth.assertThat(dao.getTrendingItems().size).isEqualTo(0)
    }


    @Test
    fun `get LocalTrending Items to DB`() = runBlocking {
        dao.putTrendingItems(Utils.getSampleCacheTrendingData())
        val result = repo.getTrendingItems()
        Truth.assertThat(dao.getTrendingItems().size).isEqualTo(3)
    }


    @Test
    fun `check if getdata is transformed from cached to list item correctly`() = runBlocking {
        val daoData = Utils.getSampleCacheTrendingData()
        dao.putTrendingItems(daoData)
        val repoData = (repo.getTrendingItems() as Result.Success).value
        for (i in repoData.indices) {
            Truth.assertThat(daoData[i].avatar).isEqualTo(repoData[i].avatar)
            Truth.assertThat(daoData[i].gitProfileName).isEqualTo(repoData[i].gitProfileName)
            Truth.assertThat(daoData[i].gitProfileUrl).isEqualTo(repoData[i].gitProfileUrl)
            Truth.assertThat(daoData[i].username).isEqualTo(repoData[i].username)
            Truth.assertThat(daoData[i].projectDesc).isEqualTo(repoData[i].projectDesc)
            Truth.assertThat(daoData[i].projectName).isEqualTo(repoData[i].projectName)
        }
    }




}