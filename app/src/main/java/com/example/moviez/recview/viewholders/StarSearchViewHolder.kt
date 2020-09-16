package com.example.moviez.recview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.databinding.ItemSearchStarBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.click_listeners.PersonClickListener

class StarSearchViewHolder(private val binding: ItemSearchStarBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        person: Person,
        clickListener: PersonClickListener
    ) {
        binding.person = person
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}