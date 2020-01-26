package com.sample.githubsample.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sample.githubsample.AppExecutors
import com.sample.githubsample.api.ApiEmptyResponse
import com.sample.githubsample.api.ApiErrorResponse
import com.sample.githubsample.api.ApiResponse
import com.sample.githubsample.api.ApiSuccessResponse
import com.sample.githubsample.vo.Error
import com.sample.githubsample.vo.Loading
import com.sample.githubsample.vo.Resource
import com.sample.githubsample.vo.Success

private const val TAG = "NetworkBoundResource"

abstract class NetworkBoundResource<RequestType, ResultType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Loading(null)
        Log.d(TAG, "Initializing")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                Log.d(TAG, "Init loading from db")
                result.value = Success(data)
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        Log.d(TAG, "fetchFromNetwork")
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                    }
                }
                is ApiErrorResponse -> {
                    result.removeSource(dbSource)
                    result.addSource(dbSource) { cachedData ->
                        setValue(Error(cachedData, response.errorMessage))
                    }
                }
                is ApiEmptyResponse -> {
                    result.removeSource(dbSource)
                    result.addSource(loadFromDb()) { cachedData ->
                        setValue(Success(cachedData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) =
        response.body

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(item: RequestType)
}