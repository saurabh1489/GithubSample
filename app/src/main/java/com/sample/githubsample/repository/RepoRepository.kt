package com.sample.githubsample.repository

import com.sample.githubsample.network.GithubService
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val githubService: GithubService
) {
    fun loadRepos() = githubService.getTrendingRepos()
}