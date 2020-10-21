package com.example.moviez.ui.tv_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.adapters.TVCollectionAdapter
import com.example.moviez.recview.click_listeners.TVClickListener
import com.example.moviez.ui.tv_collection_tab.TVCollectionsTabFragmentDirections

class TVCollectionFragment : Fragment(), TVClickListener {
    private val viewModel by viewModels<TVCollectionViewModel>()
    private lateinit var binding: LayoutRecviewBinding

    companion object {

        fun newInstance(query: TVQueryType): TVCollectionFragment {
            val fragment = TVCollectionFragment()
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

        val adapter = TVCollectionAdapter(this)

        binding.recView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val queryType: TVQueryType =
            requireNotNull(arguments).getSerializable("query") as TVQueryType

        viewModel.tvList(queryType).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            TVCollectionsTabFragmentDirections.actionTVCollectionsTabFragmentToTVDetailFragment(id)
        )
    }

    override fun onClick(tvData: TV) {
        navigateDetail(tvData.id)
    }
}