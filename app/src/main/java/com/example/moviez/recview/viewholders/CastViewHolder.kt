package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemCastBinding
import com.example.moviez.model.cast.Cast
import com.example.moviez.recview.click_listeners.CastClickListener

class CastViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        cast: Cast,
        clickListener: CastClickListener
    ) {
        binding.cast = cast
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}