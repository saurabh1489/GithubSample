package com.sample.githubsample.api

import LiveDataTestUtil.getValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.githubsample.network.GithubService
import com.sample.githubsample.util.LiveDataCallAdaptorFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GithubServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: GithubService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(LiveDataCallAdaptorFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @Test
    fun getTrendingRepos() {
        enqueueResponse("trending-repos.json")
        val repos = (getValue(service.getTrendingRepos()) as ApiSuccessResponse).body
        assertThat(repos.size, `is`(3))
        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/repositories"))
        val repo = repos[0]
        assertThat(repo.author, `is`("istio"))
        assertThat(repo.name, `is`("istio"))
        assertThat(repo.avatar, `is`("https://github.com/istio.png"))
        assertThat(repo.url, `is`("https://github.com/istio/istio"))
        assertThat(repo.description, `is`("Connect, secure, control, and observe services."))
        assertThat(repo.language, `is`("Go"))
        assertThat(repo.languageColor, `is`("#00ADD8"))
        assertThat(repo.stars, `is`(21407))
        assertThat(repo.fork, `is`(3895))
        assertThat(repo.currentPeriodStars, `is`(34))
        val repo2 = repos[1]
        assertThat(repo2.name, `is`("flax"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source!!.readString(Charsets.UTF_8)))
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}