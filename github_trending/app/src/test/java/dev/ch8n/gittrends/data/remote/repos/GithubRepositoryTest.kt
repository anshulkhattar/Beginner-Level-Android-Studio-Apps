package dev.ch8n.gittrends.data.remote.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import dev.ch8n.gittrends.MainCoroutineRule
import dev.ch8n.gittrends.data.remote.sources.GithubSource
import dev.ch8n.gittrends.di.modules.NetworkBinder
import dev.ch8n.gittrends.utils.Utils
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class GithubRepositoryTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    lateinit var source: GithubSource
    lateinit var repository: GithubRepository

    @Before
    fun setup() {
        source = mockk<GithubSource>()
        repository = GithubRepository(source)
    }


    @Test
    fun `mock and verify repository is reciving data`() = runBlocking {
        coEvery { source.getTrendingRepos(any()) } returns listOf(Utils.trendingItem)
        val result = repository.getTrendingProjects("java")
        Truth.assertThat(result.size).isEqualTo(1)
    }


    @Test
    fun `verify source data is tranformed properly to repository data`() = runBlocking {
        val sendingData = listOf(Utils.trendingItem)
        coEvery { source.getTrendingRepos(any()) } returns sendingData
        val result = repository.getTrendingProjects("java")
        for (i in result.indices) {
            Truth.assertThat(result[i].avatar).isEqualTo(sendingData[i].avatar)
            Truth.assertThat(result[i].gitProfileName).isEqualTo(sendingData[i].name)
            Truth.assertThat(result[i].username).isEqualTo(sendingData[i].username)
            Truth.assertThat(result[i].gitProfileUrl).isEqualTo(sendingData[i].url)
            Truth.assertThat(result[i].projectName).isEqualTo(sendingData[i].gitRepo.name)
            Truth.assertThat(result[i].projectDesc).isEqualTo(sendingData[i].gitRepo.description)
            Truth.assertThat(result[i].projectUrl).isEqualTo(sendingData[i].gitRepo.url)
        }
    }
}