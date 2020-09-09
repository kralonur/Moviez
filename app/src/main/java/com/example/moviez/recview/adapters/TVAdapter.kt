package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviez.databinding.ItemTvBinding
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.click_listeners.TVClickListener
import com.example.moviez.recview.viewholders.TVViewHolder

class TVAdapter(private val clickListener: TVClickListener) :
    ListAdapter<TV, TVViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<TV>() {
        override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTvBinding.inflate(inflater, parent, false)

        return TVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}