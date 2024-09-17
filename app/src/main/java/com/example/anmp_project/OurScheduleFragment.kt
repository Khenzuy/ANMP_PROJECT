package com.example.anmp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class OurScheduleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private lateinit var scheduleList: MutableList<Schedule>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_our_schedule, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Contoh data jadwal
        scheduleList = mutableListOf(
            Schedule("05 SEP", "Regional Qualifier - Valorant", "Valorant - Team A"),
            Schedule("10 SEP", "League of Legends Workshop", "LOL - Team C"),
            Schedule("07 OCT", "Call of Duty Championship", "COD - Team A"),
            Schedule("11 NOV", "Dota 2 Livestream", "Dota 2 - Team B"),
            Schedule("04 DEC", "Fortnite Invitational", "Fortnite - Team A"),
            Schedule("05 SEP", "Regional Qualifier - Valorant", "Valorant - Team A"),
            Schedule("10 SEP", "League of Legends Workshop", "LOL - Team C"),
            Schedule("07 OCT", "Call of Duty Championship", "COD - Team A"),
            Schedule("11 NOV", "Dota 2 Livestream", "Dota 2 - Team B"),
            Schedule("04 DEC", "Fortnite Invitational", "Fortnite - Team A")

        )

        adapter = ScheduleAdapter(scheduleList)
        recyclerView.adapter = adapter

        return view
    }
}
