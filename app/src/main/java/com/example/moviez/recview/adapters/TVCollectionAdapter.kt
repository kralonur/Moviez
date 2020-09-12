package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviez.databinding.ItemCollectionTvBinding
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.click_listeners.TVClickListener
import com.example.moviez.recview.viewholders.TVCollectionViewHolder

class TVCollectionAdapter(private val clickListener: TVClickListener) :
    PagedListAdapter<TV, TVCollectionViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<TV>() {
        override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVCollectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionTvBinding.inflate(inflater, parent, false)

        return TVCollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVCollectionViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}

