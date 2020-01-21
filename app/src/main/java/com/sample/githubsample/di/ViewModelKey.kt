package com.sample.githubsample.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)