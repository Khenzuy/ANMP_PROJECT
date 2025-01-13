package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentTeamBinding
import com.example.anmp_project.viewmodel.TeamViewModel
import com.squareup.picasso.Picasso

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var viewModel: TeamViewModel
    private var gamePhoto: String? = null
    private var gameName: String? = null
    private var competitionId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = TeamFragmentArgs.fromBundle(requireArguments())
        val competitionId = args.competitionId
        gamePhoto = args.gamePhoto
        gameName = args.gameName

        val imageView = binding.imageView
        Picasso.get().load(gamePhoto).into(imageView)

        setupRecyclerView()
        setupViewModel(competitionId)
    }


    private fun setupRecyclerView() {
        binding.teamRecyclerView.layoutManager = LinearLayoutManager(context)
        teamAdapter = TeamAdapter(emptyList(), gamePhoto ?: "")
        binding.teamRecyclerView.adapter = teamAdapter
    }

    private fun setupViewModel(competitionId: Int) {
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.getTeamsByCompetition(competitionId)
        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            teamAdapter.updateData(teams)
        }
    }
}



