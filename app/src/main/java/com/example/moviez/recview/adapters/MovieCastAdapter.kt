package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviez.databinding.ItemCreditMovieBinding
import com.example.moviez.model.cast.MovieCast
import com.example.moviez.recview.click_listeners.MovieCastClickListener
import com.example.moviez.recview.viewholders.MovieCastViewHolder

class MovieCastAdapter(private val clickListener: MovieCastClickListener) :
    ListAdapter<MovieCast, MovieCastViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<MovieCast>() {
        override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCreditMovieBinding.inflate(inflater, parent, false)

        return MovieCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}