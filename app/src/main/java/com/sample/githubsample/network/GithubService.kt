package com.sample.githubsample.network

import com.sample.githubsample.vo.Repo
import retrofit2.http.GET

interface GithubService {
    @GET("repositories")
    fun getTrendingRepos(): List<Repo>
}