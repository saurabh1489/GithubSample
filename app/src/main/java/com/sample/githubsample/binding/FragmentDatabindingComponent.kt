package com.sample.githubsample.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

class FragmentDatabindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
        return adapter
    }

}