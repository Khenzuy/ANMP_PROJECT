package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.FragmentTeamBinding
import com.example.anmp_project.model.TeamData
import com.squareup.picasso.Picasso

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamAdapter: TeamAdapter
    private var teams: List<TeamData> = listOf()
    private var gamePhoto: String? = null
    private var gameName: String? = null

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
        gamePhoto = args.gamePhoto
        gameName = args.gameName

        val imageView = binding.imageView
        Picasso.get().load(gamePhoto).into(imageView)

        val teamRecyclerView: RecyclerView = binding.teamRecyclerView

        teams = fetchTeamsBasedOnGameName(gameName)
        teamAdapter = TeamAdapter(teams, gamePhoto ?: "")
        teamRecyclerView.layoutManager = LinearLayoutManager(context)
        teamRecyclerView.adapter = teamAdapter
    }

    private fun fetchTeamsBasedOnGameName(gameName: String?): List<TeamData> {
        return when (gameName) {
            "Valorant" -> listOf(TeamData("Valorant Team A"), TeamData("Valorant Team B"), TeamData("Valorant Team C"))
            "Mobile Legends" -> listOf(TeamData("Mobile Legends Team A"), TeamData("Mobile Legends Team B"), TeamData("Mobile Legends Team C"))
            "League of Legends" -> listOf(TeamData("League of Legends Team A"), TeamData("League of Legends Team B"), TeamData("League of Legends Team C"))
            "Call of Duty" -> listOf(TeamData("Call of Duty Team A"), TeamData("Call of Duty Team B"), TeamData("Call of Duty Team C"))
            "Dota 2" -> listOf(TeamData("Dota 2 Team A"), TeamData("Dota 2 Team B"), TeamData("Dota 2 Team C"))
            "Fortnite" -> listOf(TeamData("Fortnite Team A"), TeamData("Fortnite Team B"), TeamData("Fortnite Team C"))
            else -> emptyList()
        }
    }
}


