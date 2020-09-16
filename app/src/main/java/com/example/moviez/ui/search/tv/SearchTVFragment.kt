package com.example.moviez.ui.search.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.adapters.TVCollectionAdapter
import com.example.moviez.recview.click_listeners.TVClickListener
import com.example.moviez.ui.search.SearchFragmentDirections
import com.example.moviez.ui.search.SearchViewModel

class SearchTVFragment : Fragment(), TVClickListener {
    private val searchViewModel by activityViewModels<SearchViewModel>()
    private val viewModel by viewModels<SearchTVViewModel>()
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

        val adapter = TVCollectionAdapter(this)
        binding.recView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        searchViewModel.query.observe(viewLifecycleOwner) {
            viewModel.updateQuery(it)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.navigateDetail.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToTVDetailFragment(it.id)
                )
            }
            viewModel.navigateToDetailDone()
        }
    }

    override fun onClick(tv_data: TV) {
        viewModel.navigateToDetail(tv_data)
    }
}