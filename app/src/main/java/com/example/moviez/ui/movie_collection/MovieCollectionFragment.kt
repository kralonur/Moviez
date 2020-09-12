package com.example.moviez.ui.movie_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.recview.adapters.MovieCollectionAdapter
import com.example.moviez.recview.click_listeners.MovieClickListener

class MovieCollectionFragment(queryType: MovieQueryType) : Fragment(), MovieClickListener {
    private val viewModel by viewModels<MovieCollectionViewModel> {
        MovieCollectionViewModel.Factory(
            queryType
        )
    }
    private lateinit var binding: LayoutRecviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutRecviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieCollectionAdapter(this)

        binding.recView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onClick(movie_data: Movie) {
        //TODO("Not yet implemented")
    }
}