package com.example.newsviewerapp.ui.feature.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsviewerapp.R

object BindingAdapter {

    @BindingAdapter("app:imageUrl","app:placeholder")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?, placeholder: Drawable) {
        Glide.with(imageView.context)
            .load(url)
            .error(placeholder)
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_baseline_folder_off_24)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().fitCenter())
            .into(imageView)
    }
}