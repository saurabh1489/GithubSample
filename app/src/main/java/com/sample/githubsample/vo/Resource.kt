package com.sample.githubsample.vo

sealed class Resource<T>(val data: T?, val message: String? = null)

class Success<T>(data: T?) : Resource<T>(data)

class Error<T>(data: T?, message: String?) : Resource<T>(data, message)

class Loading<T>(data: T?) : Resource<T>(data)