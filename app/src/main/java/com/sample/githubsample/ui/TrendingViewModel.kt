package com.sample.githubsample.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sample.githubsample.repository.RepoRepository
import com.sample.githubsample.vo.Loading
import com.sample.githubsample.vo.Repo
import com.sample.githubsample.vo.Resource
import com.sample.githubsample.vo.Success
import java.util.*
import javax.inject.Inject

const val TAG = "TrendingViewModel"

class TrendingViewModel @Inject constructor(private val repository: RepoRepository) : ViewModel() {
    private val _results = MediatorLiveData<Resource<List<Repo>>>()

    val results: LiveData<Resource<List<Repo>>>
        get() = _results

    init {
        loadRepos()
    }

    fun sortByName() {
        Log.d(TAG, "sort by name")
        val reposList = _results.value?.data
        Collections.sort(reposList) { repo1, repo2 ->
            repo1.name.compareTo(repo2.name)
        }
        _results.value = Success(reposList)
    }

    fun sortByStars() {
        Log.d(TAG, "sort by stars")
        val reposList = _results.value?.data
        Collections.sort(reposList) { repo1, repo2 ->
            repo1.stars - repo2.stars
        }
        _results.value = Success(reposList)
    }

    fun loadRepos() {
        val results = repository.loadRepos()
        _results.addSource(results) {
            _results.value = it
            if (it !is Loading) {
                _results.removeSource(results)
            }
        }
    }
}