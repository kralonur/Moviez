package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemStarBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.click_listeners.PersonClickListener

class StarViewHolder(private val binding: ItemStarBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        person: Person,
        clickListener: PersonClickListener
    ) {
        binding.person = person
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

}