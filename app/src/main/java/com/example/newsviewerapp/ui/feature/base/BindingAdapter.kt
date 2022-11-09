package com.example.newsviewerapp.ui.feature.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {
    
    @BindingAdapter("app:imageUrl","app:placeholder")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String, placeholder: Drawable) {
        Glide.with(imageView.context)
            .load(url)
            .error(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().fitCenter())
            .into(imageView)
    }
}