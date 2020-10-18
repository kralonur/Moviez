package com.example.moviez.ui.star

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.model.person.Person
import com.example.moviez.recview.adapters.StarAdapter
import com.example.moviez.recview.click_listeners.PersonClickListener

class StarFragment : Fragment(), PersonClickListener {
    private val viewModel by viewModels<StarViewModel>()
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

        val adapter = StarAdapter(this)

        binding.recView.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), getSpanCountForOrientation())
        }

        viewModel.personList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.navigateDetail.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    StarFragmentDirections.actionStarFragmentToStarDetailFragment(
                        it.id
                    )
                )
            }
            viewModel.navigateToDetailDone()
        }
    }

    override fun onClick(person_data: Person) {
        viewModel.navigateToDetail(person_data)
    }

    private fun getSpanCountForOrientation(): Int {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4
        else 2
    }
}