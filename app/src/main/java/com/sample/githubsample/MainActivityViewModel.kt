package com.sample.githubsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.githubsample.api.ApiResponse
import com.sample.githubsample.repository.RepoRepository
import com.sample.githubsample.vo.Repo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(repository: RepoRepository) : ViewModel() {
    val results: LiveData<ApiResponse<List<Repo>>> = repository.loadRepos()
}