package com.example.moviez.util

import androidx.viewpager2.widget.ViewPager2

//When viewpager render completed
fun ViewPager2.whenReady(doIt: () -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener(doIt)
}