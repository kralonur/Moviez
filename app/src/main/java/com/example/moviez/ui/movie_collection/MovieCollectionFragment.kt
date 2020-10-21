package com.example.moviez.ui.movie_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.recview.adapters.MovieCollectionAdapter
import com.example.moviez.recview.click_listeners.MovieClickListener
import com.example.moviez.ui.movie_collection_tab.MovieCollectionsTabFragmentDirections

class MovieCollectionFragment : Fragment(), MovieClickListener {
    private val viewModel by viewModels<MovieCollectionViewModel>()
    private lateinit var binding: LayoutRecviewBinding

    companion object {

        fun newInstance(query: MovieQueryType): MovieCollectionFragment {
            val fragment = MovieCollectionFragment()
            val args = Bundle()
            args.putSerializable("query", query)
            fragment.arguments = args
            return fragment
        }
    }

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

        val queryType: MovieQueryType =
            requireNotNull(arguments).getSerializable("query") as MovieQueryType

        viewModel.movieList(queryType).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            MovieCollectionsTabFragmentDirections.actionMovieCollectionsTabFragmentToMovieDetailFragment(
                id
            )
        )
    }

    override fun onClick(movieData: Movie) {
        navigateDetail(movieData.id)
    }
}