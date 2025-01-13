package com.example.anmp_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anmp_project.databinding.FragmentScheduleDetailBinding
import com.squareup.picasso.Picasso

class ScheduleDetailFragment : Fragment() {

    private var _binding: FragmentScheduleDetailBinding? = null
    private val binding get() = _binding!!

    private var eventName: String? = null
    private var month: String? = null
    private var day: Int? = null
    private var year: Int? = null
    private var esportTeam: String? = null
    private var eventPhoto: String? = null
    private var eventTime: String? = null
    private var venue: String? = null
    private var eventDescription: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            eventName = it.getString("eventName")
            month = it.getString("month")
            day = it.getInt("day")
            year = it.getInt("year")
            esportTeam = it.getString("esportTeam")
            eventPhoto = it.getString("eventPhoto")
            eventTime = it.getString("eventTime")
            venue = it.getString("venue")
            eventDescription = it.getString("eventDescription")
        }

        binding.scheduleTitleTextView.text = eventName
        binding.eventTimeTextView.text = "$day $month $year"
        binding.teamNameTextView.text = esportTeam
        binding.locationTextView.text = venue
        binding.scheduleDescriptionTextView.text = eventDescription

        Picasso.get()
            .load(eventPhoto)
            .into(binding.scheduleImageView)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
