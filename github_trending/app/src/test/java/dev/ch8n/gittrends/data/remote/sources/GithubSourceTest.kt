package dev.ch8n.gittrends.data.remote.sources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import dev.ch8n.gittrends.MainCoroutineRule
import dev.ch8n.gittrends.data.remote.config.ApiManager
import dev.ch8n.gittrends.data.remote.config.BaseUrl
import dev.ch8n.gittrends.di.modules.NetworkBinder
import dev.ch8n.gittrends.utils.Result
import dev.ch8n.gittrends.utils.Utils
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class GithubSourceTest {


    /**
     * todo:
     * test Source if getting real response
     */

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private var server: MockWebServer? = null

    lateinit var source: GithubSource

    @Before
    fun setup() {

        val networkBinder = NetworkBinder()
        val okClient = networkBinder.provideOkHttpClient(mockk(relaxed = true))
        val retrofit = networkBinder.provideRetrofitClient(okClient)
        val apiManager = networkBinder.provideApiManager(retrofit)
        server = MockWebServer()
        source = GithubSource(apiManager)

    }

    @Test
    fun `check if real api is responding`() = runBlocking {
        val result = source.getTrendingRepos("java")
        Truth.assertThat(result.size).isNotEqualTo(0)
    }


    @Test
    fun `mock real api is responding`() = runBlocking {
        val networkBinder = NetworkBinder()
        val okClient = networkBinder.provideOkHttpClient(mockk(relaxed = true))
        val retrofit = networkBinder.provideRetrofitClient(okClient)
        val spyApiManager = spyk(networkBinder.provideApiManager(retrofit))
        source = GithubSource(spyApiManager)
        coEvery { spyApiManager.githubService.getTrendingRepos() } returns listOf(Utils.trendingItem)
        val result = source.getTrendingRepos("java")
        Truth.assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun `mockServer api is responding 200`() = runBlocking {
        val baseUrl = "/"
        val testDataJson = "[\n" +
                "  {\n" +
                "    \"username\": \"chrisbanes\",\n" +
                "    \"name\": \"Chris Banes\",\n" +
                "    \"type\": \"user\",\n" +
                "    \"url\": \"https://github.com/chrisbanes\",\n" +
                "    \"avatar\": \"https://avatars0.githubusercontent.com/u/227486\",\n" +
                "    \"repo\": {\n" +
                "      \"name\": \"PhotoView\",\n" +
                "      \"description\": \"Implementation of ImageView for Android that supports zooming, by various touch gestures.\",\n" +
                "      \"url\": \"https://github.com/chrisbanes/PhotoView\"\n" +
                "    }\n" +
                "  }\n" +
                "]"
        val url = server?.url(baseUrl)

        val networkBinder = NetworkBinder()
        val okClient = networkBinder.provideOkHttpClient(mockk(relaxed = true))
        val retrofit = Retrofit.Builder()
            .baseUrl(url.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient)
            .build()

        val apiManager = networkBinder.provideApiManager(retrofit)
        source = GithubSource(apiManager)

        server?.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(testDataJson)
        )

        val result = source.getTrendingRepos("java")
        Truth.assertThat(result.size).isEqualTo(1)
    }


    @Test
    fun `mockServer api is responding 404 exception would be thrown`() = runBlocking {
        val baseUrl = "/"
        val url = server?.url(baseUrl)

        val networkBinder = NetworkBinder()
        val okClient = networkBinder.provideOkHttpClient(mockk(relaxed = true))
        val retrofit = Retrofit.Builder()
            .baseUrl(url.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient)
            .build()

        val apiManager = networkBinder.provideApiManager(retrofit)
        source = GithubSource(apiManager)

        server?.enqueue(
            MockResponse().setResponseCode(404)
        )

        val result = Result.build { source.getTrendingRepos("java") }

        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).error.toString()).isEqualTo("repos not found")

    }

    @After
    fun teardown() {
        server?.shutdown()
    }

}