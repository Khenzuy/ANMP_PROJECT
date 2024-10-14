package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentTeamDetailBinding
import com.example.anmp_project.model.TeamDetail

class TeamDetailFragment : Fragment() {

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
        teamName = args.teamName

        binding.txtTeamName.text = teamName

        val teamDetails = fetchTeamDetailsBasedOnTeamName(teamName)
        teamDetailAdapter = TeamDetailAdapter(teamDetails)
        binding.teamDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.teamDetailRecyclerView.adapter = teamDetailAdapter
    }

    private fun fetchTeamDetailsBasedOnTeamName(teamName: String?): List<TeamDetail> {
        return when (teamName) {
            "Valorant Team A" -> listOf(
                TeamDetail("Role 1 Valorant", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 Valorant", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 Valorant", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 Valorant", "Player 4 A", "url_to_image"),
                TeamDetail("Role 5 Valorant", "Player 5 A", "url_to_image")
            )
            "Valorant Team B" -> listOf(
                TeamDetail("Role 1 Valorant", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 Valorant", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 Valorant", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 Valorant", "Player 4 B", "url_to_image"),
                TeamDetail("Role 5 Valorant", "Player 5 B", "url_to_image")
            )
            "Valorant Team C" -> listOf(
                TeamDetail("Role 1 Valorant", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 Valorant", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 Valorant", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 Valorant", "Player 4 C", "url_to_image"),
                TeamDetail("Role 5 Valorant", "Player 5 C", "url_to_image")
            )
            "Mobile Legends Team A" -> listOf(
                TeamDetail("Role 1 Mobile Legends", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 Mobile Legends", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 Mobile Legends", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 Mobile Legends", "Player 4 A", "url_to_image"),
                TeamDetail("Role 5 Mobile Legends", "Player 5 A", "url_to_image")
            )
            "Mobile Legends Team B" -> listOf(
                TeamDetail("Role 1 Mobile Legends", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 Mobile Legends", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 Mobile Legends", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 Mobile Legends", "Player 4 B", "url_to_image"),
                TeamDetail("Role 5 Mobile Legends", "Player 5 B", "url_to_image")
            )
            "Mobile Legends Team C" -> listOf(
                TeamDetail("Role 1 Mobile Legends", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 Mobile Legends", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 Mobile Legends", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 Mobile Legends", "Player 4 C", "url_to_image"),
                TeamDetail("Role 5 Mobile Legends", "Player 5 C", "url_to_image")
            )
            "League of Legends Team A" -> listOf(
                TeamDetail("Role 1 League of Legends", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 League of Legends", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 League of Legends", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 League of Legends", "Player 4 A", "url_to_image"),
                TeamDetail("Role 5 League of Legends", "Player 5 A", "url_to_image")
            )
            "League of Legends Team B" -> listOf(
                TeamDetail("Role 1 League of Legends", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 League of Legends", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 League of Legends", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 League of Legends", "Player 4 B", "url_to_image"),
                TeamDetail("Role 5 League of Legends", "Player 5 B", "url_to_image")
            )
            "League of Legends Team C" -> listOf(
                TeamDetail("Role 1 League of Legends", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 League of Legends", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 League of Legends", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 League of Legends", "Player 4 C", "url_to_image"),
                TeamDetail("Role 5 League of Legends", "Player 5 C", "url_to_image")
            )
            "Call of Duty Team A" -> listOf(
                TeamDetail("Role 1 Call of Duty Team", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 Call of Duty Team", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 Call of Duty Team", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 Call of Duty Team", "Player 4 A", "url_to_image"),
                TeamDetail("Role 5 Call of Duty Team", "Player 5 A", "url_to_image")
            )
            "Call of Duty Team B" -> listOf(
                TeamDetail("Role 1 Call of Duty Team", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 Call of Duty Team", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 Call of Duty Team", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 Call of Duty Team", "Player 4 B", "url_to_image"),
                TeamDetail("Role 5 Call of Duty Team", "Player 5 B", "url_to_image")
            )
            "Call of Duty Team C" -> listOf(
                TeamDetail("Role 1 Call of Duty Team", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 Call of Duty Team", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 Call of Duty Team", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 Call of Duty Team", "Player 4 C", "url_to_image"),
                TeamDetail("Role 5 Call of Duty Team", "Player 5 C", "url_to_image")
            )
            "Dota 2 Team A" -> listOf(
                TeamDetail("Role 1 Dota 2", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 Dota 2", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 Dota 2", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 Dota 2", "Player 4 A", "url_to_image"),
                TeamDetail("Role 5 Dota 2", "Player 5 A", "url_to_image")
            )
            "Dota 2 Team B" -> listOf(
                TeamDetail("Role 1 Dota 2", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 Dota 2", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 Dota 2", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 Dota 2", "Player 4 B", "url_to_image"),
                TeamDetail("Role 5 Dota 2", "Player 5 B", "url_to_image")
            )
            "Dota 2 Team C" -> listOf(
                TeamDetail("Role 1 Dota 2", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 Dota 2", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 Dota 2", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 Dota 2", "Player 4 C", "url_to_image"),
                TeamDetail("Role 5 Dota 2", "Player 5 C", "url_to_image")
            )
            "Fortnite Team A" -> listOf(
                TeamDetail("Role 1 Fortnite", "Player 1 A", "url_to_image"),
                TeamDetail("Role 2 Fortnite", "Player 2 A", "url_to_image"),
                TeamDetail("Role 3 Fortnite", "Player 3 A", "url_to_image"),
                TeamDetail("Role 4 Fortnite", "Player 4 A", "url_to_image")
            )
            "Fortnite Team B" -> listOf(
                TeamDetail("Role 1 Fortnite", "Player 1 B", "url_to_image"),
                TeamDetail("Role 2 Fortnite", "Player 2 B", "url_to_image"),
                TeamDetail("Role 3 Fortnite", "Player 3 B", "url_to_image"),
                TeamDetail("Role 4 Fortnite", "Player 4 B", "url_to_image")
            )
            "Fortnite Team C" -> listOf(
                TeamDetail("Role 1 Fortnite", "Player 1 C", "url_to_image"),
                TeamDetail("Role 2 Fortnite", "Player 2 C", "url_to_image"),
                TeamDetail("Role 3 Fortnite", "Player 3 C", "url_to_image"),
                TeamDetail("Role 4 Fortnite", "Player 4 C", "url_to_image")
            )
            else -> emptyList()
        }
    }
}

