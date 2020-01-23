package com.sample.githubsample.db

import LiveDataTestUtil.getValue
import TestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {
        val repos = TestUtil.createRepos(10, "saurabh", "repo", "Description")
        db.repoDao().insertRepos(repos)
        val loaded = getValue(db.repoDao().getRepos())
        assertThat(loaded, notNullValue())
        assertThat(loaded.size, `is`(10))
        val repo = loaded[0]
        assertThat(repo.author, `is`("saurabh0"))
        assertThat(repo.name, `is`("repo0"))
        assertThat(repo.description, `is`("Description0"))
        assertThat(repo.fork, `is`(1))
        assertThat(repo.stars, `is`(2))
        assertThat(repo.currentPeriodStars, `is`(3))
    }
}