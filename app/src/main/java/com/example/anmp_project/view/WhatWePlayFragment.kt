package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentWhatWePlayBinding
import com.example.anmp_project.viewmodel.WhatWePlayViewModel

class WhatWePlayFragment : Fragment() {

    private lateinit var binding: FragmentWhatWePlayBinding
    private lateinit var viewModel: WhatWePlayViewModel
    private val whatWePlayAdapter  = WhatWePlayAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhatWePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshLayout.setOnRefreshListener {
            binding.competitionRecyclerView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        viewModel = ViewModelProvider(this).get(WhatWePlayViewModel::class.java)
        viewModel.refresh()

        binding.competitionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.competitionRecyclerView.adapter = whatWePlayAdapter

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.competitionsLD.observe(viewLifecycleOwner, Observer {
            whatWePlayAdapter.updateCompetitionList(it)
        })
        viewModel.competitionLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.competitionRecyclerView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.competitionRecyclerView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

    }
}
