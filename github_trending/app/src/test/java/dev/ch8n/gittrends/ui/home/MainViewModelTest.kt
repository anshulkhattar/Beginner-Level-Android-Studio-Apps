package dev.ch8n.gittrends.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import com.jraska.livedata.test
import dev.ch8n.gittrends.MainCoroutineRule
import dev.ch8n.gittrends.data.others.repos.CacheTrendingRepo
import dev.ch8n.gittrends.data.remote.repos.GithubRepo
import dev.ch8n.gittrends.utils.Result
import dev.ch8n.gittrends.utils.Utils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class MainViewModelTest {


    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel
    lateinit var githubRepo: GithubRepo
    lateinit var cacheRepo: CacheTrendingRepo

    @Before
    fun setup() {
        githubRepo = mockk(relaxed = true)
        cacheRepo = mockk(relaxed = true)
        viewModel = MainViewModel(githubRepo, cacheRepo)
    }

    @Test
    fun `verify local Data was read from DB`() {

        coEvery { githubRepo.getTrendingProjects(any()) } returns Utils.getSampleTrendingData()

        val expectingResult = Result.build { Utils.getSampleTrendingData() }
        coEvery { cacheRepo.getTrendingItems() } returns expectingResult

        val result = viewModel.getTrendingProjects("java")
            .test()
            .valueHistory()
            .filter {
                (it as Result.Success).value == (expectingResult as Result.Success).value
            }.size

        Truth.assertThat(result).isEqualTo(1)

    }

    @Test
    fun `verify remote Data was Fetached from remote`() {

        coEvery { githubRepo.getTrendingProjects(any()) } returns Utils.getSampleTrendingData()
        viewModel.getTrendingProjects("java")
            .test()
            .assertValue {
                (it as Result.Success).value.size == 3
            }

    }

    @Test
    fun `verify remote Data was cached`() {

        val expectingResult = Utils.getSampleTrendingData()

        coEvery { githubRepo.getTrendingProjects(any()) } returns expectingResult

        coEvery { cacheRepo.getTrendingItems() } returns Result.build { Utils.getSampleTrendingData() }

        viewModel.getTrendingProjects("java").test()

        coVerify { cacheRepo.putTrendingItems(any()) }

    }

}