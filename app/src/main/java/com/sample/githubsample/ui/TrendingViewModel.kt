package com.sample.githubsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.githubsample.repository.RepoRepository
import com.sample.githubsample.vo.Repo
import com.sample.githubsample.vo.Resource
import javax.inject.Inject

class TrendingViewModel @Inject constructor(repository: RepoRepository) : ViewModel() {
    val results: LiveData<Resource<List<Repo>>> = repository.loadRepos()
}