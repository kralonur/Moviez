package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviez.databinding.ItemCollectionMovieBinding
import com.example.moviez.model.movie.Movie
import com.example.moviez.recview.click_listeners.MovieClickListener
import com.example.moviez.recview.viewholders.MovieCollectionViewHolder

class MovieCollectionAdapter(private val clickListener: MovieClickListener) :
    PagedListAdapter<Movie, MovieCollectionViewHolder>(ListItemCallback()) {


    private class ListItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCollectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionMovieBinding.inflate(inflater, parent, false)

        return MovieCollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCollectionViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}