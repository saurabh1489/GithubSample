package com.sample.githubsample.binding

import android.view.View
import android.view.ViewGroup
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

    @BindingAdapter("android:layout_marginBottom")
    fun setLayoutMarginBottom(view: View, dimen: Float) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin = dimen.toInt()
        view.layoutParams = layoutParams
    }
}