package com.sample.githubsample.network

import androidx.lifecycle.LiveData
import com.sample.githubsample.api.ApiResponse
import com.sample.githubsample.vo.Repo
import retrofit2.http.GET

interface GithubService {
    @GET("repositories")
    fun getTrendingRepos(): LiveData<ApiResponse<List<Repo>>>
}