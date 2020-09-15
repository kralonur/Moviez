package com.example.moviez.recview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviez.databinding.ItemCreditTvBinding
import com.example.moviez.model.cast.TVCast
import com.example.moviez.recview.click_listeners.TVCastClickListener
import com.example.moviez.recview.viewholders.TVCastViewHolder

class TVCastAdapter(private val clickListener: TVCastClickListener) :
    ListAdapter<TVCast, TVCastViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<TVCast>() {
        override fun areItemsTheSame(oldItem: TVCast, newItem: TVCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVCast, newItem: TVCast): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCreditTvBinding.inflate(inflater, parent, false)

        return TVCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVCastViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }
}