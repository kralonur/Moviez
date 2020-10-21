package com.example.moviez.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.example.moviez.GlideApp
import com.example.moviez.R

@BindingAdapter("bindImage")
fun AppCompatImageView.bindImage(imagePath: String?) {
    imagePath?.let {
        GlideApp.with(context)
            .load(it)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}

@BindingAdapter("bindBackdropImage")
fun AppCompatImageView.bindBackdropImage(imagePath: String?) {
    imagePath?.let {
        bindImage(Constants.TMDB.BACKDROP_SIZE_780 + imagePath)
    }
}

@BindingAdapter("bindProfileImage")
fun AppCompatImageView.bindProfileImage(imagePath: String?) {
    imagePath?.let {
        bindImage(Constants.TMDB.PROFILE_SIZE_185 + imagePath)
    }
}

@BindingAdapter("bindCastImage")
fun AppCompatImageView.bindCastImage(imagePath: String?) {
    imagePath?.let {
        bindImage(Constants.TMDB.PROFILE_SIZE_185 + imagePath)
    }
}

@BindingAdapter("bindPosterImage")
fun AppCompatImageView.bindPosterImage(imagePath: String?) {
    imagePath?.let {
        bindImage(Constants.TMDB.PROFILE_SIZE_185 + imagePath)
    }
}