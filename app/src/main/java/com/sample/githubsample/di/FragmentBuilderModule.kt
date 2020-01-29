package com.sample.githubsample.di

import com.sample.githubsample.ui.TrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeTrendingFragment(): TrendingFragment
}