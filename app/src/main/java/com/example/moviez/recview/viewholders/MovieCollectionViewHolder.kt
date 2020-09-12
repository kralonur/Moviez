package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemCollectionMovieBinding
import com.example.moviez.model.movie.Movie
import com.example.moviez.recview.click_listeners.MovieClickListener

class MovieCollectionViewHolder(private val binding: ItemCollectionMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        movie: Movie,
        clickListener: MovieClickListener
    ) {
        binding.movie = movie
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}