package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviez.databinding.ItemSearchStarBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.click_listeners.PersonClickListener
import com.example.moviez.recview.viewholders.StarSearchViewHolder

class StarSearchAdapter(private val clickListener: PersonClickListener) :
    PagingDataAdapter<Person, StarSearchViewHolder>(ListItemCallback()) {


    private class ListItemCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchStarBinding.inflate(inflater, parent, false)

        return StarSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarSearchViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}