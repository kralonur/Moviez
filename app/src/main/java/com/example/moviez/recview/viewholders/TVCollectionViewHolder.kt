package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemCollectionTvBinding
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.click_listeners.TVClickListener

class TVCollectionViewHolder(private val binding: ItemCollectionTvBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        tv: TV,
        clickListener: TVClickListener
    ) {
        binding.tv = tv
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}