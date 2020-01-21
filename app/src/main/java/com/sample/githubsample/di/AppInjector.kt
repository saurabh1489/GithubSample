package com.sample.githubsample.di

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.sample.githubsample.GithubApp
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

object AppInjector {
    fun init(githubApp: GithubApp) {
        DaggerAppComponent.builder().application(githubApp)
            .build().inject(githubApp)
        githubApp
            .registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityResumed(activity: Activity) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }
    }
}