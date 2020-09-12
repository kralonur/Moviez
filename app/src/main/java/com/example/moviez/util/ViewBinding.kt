package com.example.moviez.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.moviez.R
import com.google.android.material.textview.MaterialTextView
import com.soywiz.klock.DateFormat
import com.soywiz.klock.KlockLocale
import com.soywiz.klock.parse

@BindingAdapter("bindImage")
fun AppCompatImageView.bindImage(imagePath: String?) {
    imagePath?.let {
        Glide.with(context)
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

@BindingAdapter("bindYear")
fun MaterialTextView.bindYear(date: String?) {
    if (date == null || date.isBlank()) {
        this.text = "????"
    } else {
        val dateFormat = DateFormat("yyyy-MM-dd")
        this.text = dateFormat.parse(date).year.year.toString()
    }
}

@BindingAdapter("bindDate")
fun MaterialTextView.bindDate(date: String?) {
    if (date == null || date.isBlank()) {
        this.text = "????"
    } else {
        val dateFormat = DateFormat("yyyy-MM-dd")
        val locale = KlockLocale.english
        val finalDate = dateFormat.parse(date)
        this.text = locale.formatDateLong.format(finalDate)
    }
}

@BindingAdapter("bindProfileImage")
fun AppCompatImageView.bindProfileImage(imagePath: String?) {
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