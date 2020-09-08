package com.example.moviez.recview.click_listeners

import com.example.moviez.model.movie.Movie

interface MovieClickListener {
    fun onClick(movie_data: Movie)
}