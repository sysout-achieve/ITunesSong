package com.gunt.itunessong.ui.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gunt.itunessong.R

object ImageViewExt {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun ImageView.loadImage(imgUrl: String?) {
        Glide.with(this)
            .load(imgUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(this)
    }
}
