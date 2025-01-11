package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentWhatWePlayBinding
import com.example.anmp_project.model.Competition
import com.example.anmp_project.viewmodel.WhatWePlayViewModel

class WhatWePlayFragment : Fragment() {

    private lateinit var binding: FragmentWhatWePlayBinding
    private lateinit var viewModel: WhatWePlayViewModel
    private val whatWePlayAdapter = WhatWePlayAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhatWePlayBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(WhatWePlayViewModel::class.java)

        setupRecyclerView()
        setupSwipeRefreshLayout()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.competitionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = whatWePlayAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        viewModel.competitionsLD.observe(viewLifecycleOwner) { competitions ->
            competitions?.let {
                whatWePlayAdapter.updateCompetitionList(it as ArrayList<Competition>)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.refreshLayout.isRefreshing = isLoading
        }

        viewModel.errorState.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                binding.txtError.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            } else {
                binding.txtError.visibility = View.GONE
            }
        }
    }
}
