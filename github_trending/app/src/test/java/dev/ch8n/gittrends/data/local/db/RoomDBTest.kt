package dev.ch8n.gittrends.data.local.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dev.ch8n.gittrends.MainCoroutineRule
import dev.ch8n.gittrends.di.modules.DataBaseBinder
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
@SmallTest
class RoomDBTest {

    /**
     * todo:
     * test db provided dao is working
     * 1. insert into dao
     * 2. delete from dao
     * 3. get from dao
     */

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: RoomDB

    @Before
    fun initDatabase() {

        val databaseBinder = DataBaseBinder()
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = databaseBinder.provideGitTrendDB(
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                RoomDB::class.java
            ).allowMainThreadQueries()
        )

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun `insert database into db and get from db`() {
        runBlocking {
            database.trendingItemDao().putTrendingItems(Utils.getSampleCacheTrendingData())
            val loadeditems = database.trendingItemDao().getTrendingItems()
            Truth.assertThat(loadeditems.size).isEqualTo(3)
        }
    }

    @Test
    fun `delete database into db and get empty from db`() {
        runBlocking {
            database.trendingItemDao().putTrendingItems(Utils.getSampleCacheTrendingData())
            val loadeditems = database.trendingItemDao().getTrendingItems()
            Truth.assertThat(loadeditems.size).isEqualTo(3)

            database.trendingItemDao().deleteAllItems()
            val loadedafterDelete = database.trendingItemDao().getTrendingItems()
            Truth.assertThat(loadedafterDelete.size).isEqualTo(0)
        }
    }



}