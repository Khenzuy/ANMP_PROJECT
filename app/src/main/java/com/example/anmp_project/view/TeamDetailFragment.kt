package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.R
import com.example.anmp_project.databinding.FragmentTeamDetailBinding
import com.example.anmp_project.viewmodel.TeamDetailViewModel
import com.squareup.picasso.Picasso

class TeamDetailFragment : Fragment() {

    private lateinit var viewModel: TeamDetailViewModel
    private lateinit var binding: FragmentTeamDetailBinding
    private lateinit var teamDetailAdapter: TeamDetailAdapter
    private var teamName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = TeamDetailFragmentArgs.fromBundle(requireArguments())
        val teamName = args.teamName
        val gamePhoto = args.gamePhoto

        binding.txtTeamName.text = teamName

        if (gamePhoto != null) {
            Picasso.get().load(gamePhoto).into(binding.imageGame)
        } else {
            binding.imageGame.setImageResource(R.drawable.baseline_people_24)
        }
        setupRecyclerView()
        setupViewModel(teamName)
    }

    private fun setupRecyclerView() {
        binding.teamDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        teamDetailAdapter = TeamDetailAdapter(emptyList())
        binding.teamDetailRecyclerView.adapter = teamDetailAdapter
    }

    private fun setupViewModel(teamName: String) {
        viewModel = ViewModelProvider(this).get(TeamDetailViewModel::class.java)
        viewModel.getMembersByTeamName(teamName)
        viewModel.members.observe(viewLifecycleOwner) { members ->
            teamDetailAdapter.updateData(members)
        }
    }
}