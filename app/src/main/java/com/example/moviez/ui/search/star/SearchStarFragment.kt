package com.example.moviez.ui.search.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.adapters.StarSearchAdapter
import com.example.moviez.recview.click_listeners.PersonClickListener
import com.example.moviez.ui.search.SearchFragmentDirections
import com.example.moviez.ui.search.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchStarFragment : Fragment(), PersonClickListener {
    private val searchViewModel by activityViewModels<SearchViewModel>()
    private val viewModel by viewModels<SearchStarViewModel>()
    private lateinit var binding: LayoutRecviewBinding
    private val pagingAdapter = StarSearchAdapter(this)

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

        binding.recView.apply {
            this.adapter = pagingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        searchViewModel.query.observe(viewLifecycleOwner) {
            viewModel.updateQuery(it)
        }

        viewModel.query.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getStarPagingFlow(it).collectLatest { data ->
                    pagingAdapter.submitData(data)
                }
            }
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToStarDetailFragment(id)
        )
    }

    override fun onClick(personData: Person) {
        navigateDetail(personData.id)
    }
}