package com.example.anmp_project // Replace with your package name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentMainBinding // Replace with your package name

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val competitions = listOf(
            Competition(R.drawable.valorant, "Valorant","Valorant is a tactical first-person shooter (FPS) developed and published by Riot Games. The game combines precise gunplay with strategic team-based elements. Set in a near-future Earth, players assume the role of \"agents\" with unique abilities and engage in intense 5v5 matches where one team attempts to plant a device known as the Spike while the other team tries to prevent it. Success in Valorant relies on teamwork, strategy, and sharp shooting skills."), 
            Competition(R.drawable.mobile_legend, "Mobile Legends: Bang Bang","Mobile Legends: Bang Bang is a popular multiplayer online battle arena (MOBA) game developed and published by Moonton. Players engage in 5v5 battles in a fantasy-themed arena where teamwork and strategy are key to victory. Each player controls a hero with unique abilities and works with their team to destroy the enemy's base while defending their own. The game emphasizes quick matches, strategic gameplay, and cooperative team effort."),
        )

        val competitionAdapter = CompetitionAdapter(competitions)
        binding.competitionRecyclerView.adapter = competitionAdapter
        binding.competitionRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}