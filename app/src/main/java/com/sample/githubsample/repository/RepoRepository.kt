package com.sample.githubsample.repository

import androidx.lifecycle.LiveData
import com.sample.githubsample.AppExecutors
import com.sample.githubsample.api.ApiResponse
import com.sample.githubsample.db.RepoDao
import com.sample.githubsample.network.GithubService
import com.sample.githubsample.vo.Repo
import com.sample.githubsample.vo.Resource
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val githubService: GithubService,
    private val repoDao: RepoDao
) {
    fun loadRepos(): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
            override fun loadFromDb() = repoDao.getRepos()

            override fun shouldFetch(data: List<Repo>?) = true

            override fun createCall() = githubService.getTrendingRepos()

            override fun saveCallResult(item: List<Repo>) = repoDao.insertRepos(item)

        }.asLiveData()
    }
}