package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemCreditTvBinding
import com.example.moviez.model.cast.TVCast
import com.example.moviez.recview.click_listeners.TVCastClickListener

class TVCastViewHolder(private val binding: ItemCreditTvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        tvCast: TVCast,
        clickListener: TVCastClickListener
    ) {
        binding.tvCast = tvCast
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}