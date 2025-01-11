package com.example.anmp_project.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.R
import com.example.anmp_project.databinding.FragmentApplyTeamListBinding
import com.example.anmp_project.adapters.ApplyTeamListAdapter
import com.example.anmp_project.viewmodel.ApplyTeamListViewModel

class ApplyTeamListFragment : Fragment() {

    private lateinit var binding: FragmentApplyTeamListBinding
    private lateinit var viewModel: ApplyTeamListViewModel
    private lateinit var applyTeamListAdapter: ApplyTeamListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentApplyTeamListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ApplyTeamListViewModel::class.java)
        applyTeamListAdapter = ApplyTeamListAdapter(listOf())

        binding.proposalRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = applyTeamListAdapter
        }

        viewModel.proposals.observe(viewLifecycleOwner) { proposals ->
            if (proposals != null && proposals.isNotEmpty()) {
                applyTeamListAdapter.updateProposals(proposals)
            }
        }

        viewModel.refresh(getUsernameFromPreferences())

        binding.floatingActionButton.setOnClickListener {
            navigateToSubmitFragment()
        }
    }

    private fun getUsernameFromPreferences(): String {
        return requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE).getString("username", "") ?: ""
    }

    private fun navigateToSubmitFragment() {
        findNavController().navigate(R.id.action_applyTeamListFragment_to_applyTeamSubmitFragment)
    }
}
