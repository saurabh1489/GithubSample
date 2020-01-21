package com.sample.githubsample.di

import com.sample.githubsample.network.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }
}