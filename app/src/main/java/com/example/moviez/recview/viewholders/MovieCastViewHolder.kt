package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemCreditMovieBinding
import com.example.moviez.model.cast.MovieCast
import com.example.moviez.recview.click_listeners.MovieCastClickListener

class MovieCastViewHolder(private val binding: ItemCreditMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movieCast: MovieCast,
        clickListener: MovieCastClickListener
    ) {
        binding.movieCast = movieCast
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}