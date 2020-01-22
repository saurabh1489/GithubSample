package com.sample.githubsample.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.githubsample.network.GithubService
import com.sample.githubsample.util.LiveDataCallAdaptorFactory
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
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
            .baseUrl("https://github-trending-api.now.sh")
            .addCallAdapterFactory(LiveDataCallAdaptorFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @Test
    fun getTrendingRepos() {

    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}