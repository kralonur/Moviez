package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviez.databinding.ItemStarBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.click_listeners.PersonClickListener
import com.example.moviez.recview.viewholders.StarViewHolder


class StarAdapter(private val clickListener: PersonClickListener) :
    PagedListAdapter<Person, StarViewHolder>(ListItemCallback()) {


    private class ListItemCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStarBinding.inflate(inflater, parent, false)

        return StarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}