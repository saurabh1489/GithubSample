package com.sample.githubsample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.githubsample.MainActivityViewModel
import com.sample.githubsample.viewmodel.GithubViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}