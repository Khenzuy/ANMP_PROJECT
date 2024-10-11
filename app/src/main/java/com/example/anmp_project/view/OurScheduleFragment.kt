package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.R


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

        scheduleList = mutableListOf(
            Schedule(
                "05 SEP",
                "Regional Qualifier - Valorant",
                "Valorant - Team A",
                "Los Angeles, CA",
                "This high-stakes event will bring together top teams from across the region, all competing for a chance to advance to the national finals. Expect intense gameplay, strategic plays, and thrilling moments."
            ),
            Schedule(
                "10 SEP",
                "League of Legends Workshop",
                "LOL - Team C",
                "San Francisco, CA",
                "Join us for an in-depth workshop on League of Legends strategies and team compositions, led by expert coaches and players."
            ),
            Schedule(
                "07 OCT",
                "Call of Duty Championship",
                "COD - Team A",
                "New York, NY",
                "The ultimate Call of Duty Championship where top teams battle it out for the title. Watch intense matches and high-level gameplay."
            ),
            Schedule(
                "11 NOV",
                "Dota 2 Livestream",
                "Dota 2 - Team B",
                "Seattle, WA",
                "Catch all the action live as Team B competes in an exciting Dota 2 tournament. Expect thrilling moments and epic plays."
            ),
            Schedule(
                "04 DEC",
                "Fortnite Invitational",
                "Fortnite - Team A",
                "Austin, TX",
                "An invitation-only Fortnite event featuring top players and teams. Enjoy competitive gameplay and unique in-game events."
            ),
            Schedule(
                "05 SEP",
                "Regional Qualifier - Valorant",
                "Valorant - Team A",
                "Los Angeles, CA",
                "This high-stakes event will bring together top teams from across the region, all competing for a chance to advance to the national finals. Expect intense gameplay, strategic plays, and thrilling moments."
            ),
            Schedule(
                "10 SEP",
                "League of Legends Workshop",
                "LOL - Team C",
                "San Francisco, CA",
                "Join us for an in-depth workshop on League of Legends strategies and team compositions, led by expert coaches and players."
            ),
            Schedule(
                "04 DEC",
                "Fortnite Invitational",
                "Fortnite - Team A",
                "Austin, TX",
                "An invitation-only Fortnite event featuring top players and teams. Enjoy competitive gameplay and unique in-game events."
            ),
            Schedule(
                "05 SEP",
                "Regional Qualifier - Valorant",
                "Valorant - Team A",
                "Los Angeles, CA",
                "This high-stakes event will bring together top teams from across the region, all competing for a chance to advance to the national finals. Expect intense gameplay, strategic plays, and thrilling moments."
            ),
            Schedule(
                "10 SEP",
                "League of Legends Workshop",
                "LOL - Team C",
                "San Francisco, CA",
                "Join us for an in-depth workshop on League of Legends strategies and team compositions, led by expert coaches and players."
            )
        )

        adapter = ScheduleAdapter(scheduleList)
        recyclerView.adapter = adapter

        return view
    }
}
