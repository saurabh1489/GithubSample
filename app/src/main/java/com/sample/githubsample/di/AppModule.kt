package com.sample.githubsample.di

import android.app.Application
import androidx.room.Room
import com.sample.githubsample.db.GithubDb
import com.sample.githubsample.db.RepoDao
import com.sample.githubsample.network.GithubService
import com.sample.githubsample.util.LiveDataCallAdaptorFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh")
            .addCallAdapterFactory(LiveDataCallAdaptorFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): GithubDb {
        return Room.databaseBuilder(app, GithubDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providRepoDao(db: GithubDb): RepoDao {
        return db.repoDao()
    }
}