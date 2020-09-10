package com.example.moviez.ui.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviez.databinding.FragmentStarBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.adapters.StarAdapter
import com.example.moviez.recview.click_listeners.PersonClickListener

class StarFragment : Fragment(), PersonClickListener {
    private val viewModel by viewModels<StarViewModel>()
    private lateinit var binding: FragmentStarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StarAdapter(this)
        binding.recView.adapter = adapter

        viewModel.personList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClick(person_data: Person) {
        //TODO("Not yet implemented")
    }
}