package com.sample.githubsample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.githubsample.vo.Repo

@Database(
    entities = [
        Repo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GithubDb : RoomDatabase() {

    abstract fun repoDao(): RepoDao

}