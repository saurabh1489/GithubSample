package com.sample.githubsample.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import javax.inject.Inject

class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter(value = ["imageUrl"])
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).circleCrop().into(imageView)
    }
}