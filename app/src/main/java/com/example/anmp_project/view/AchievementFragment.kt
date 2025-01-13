package com.example.anmp_project.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentAchievementBinding
import com.example.anmp_project.model.Achievement
import com.squareup.picasso.Picasso

class AchievementFragment : Fragment() {

    private lateinit var binding: FragmentAchievementBinding
    private lateinit var achievementsAdapter: AchievementAdapter
    private var originalAchievements: List<Achievement> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val image = AchievementFragmentArgs.fromBundle(it).gamePhoto
            val nameGame = AchievementFragmentArgs.fromBundle(it).gameName
            val teamAchievements = AchievementFragmentArgs.fromBundle(it).teamAchievement

            Picasso.get().load(image).into(binding.imageView)
            binding.txtGameName.text = nameGame

            originalAchievements = teamAchievements.joinToString().split(", ").mapNotNull { str ->
                val parts = str.split(";")
                if (parts.size >= 3) {
                    Achievement(
                        id = 0,
                        achievement = parts[1],
                        teamName = parts[0],
                        year = parts.getOrNull(2)?.toIntOrNull() ?: 0,
                        competitionId = 0
                    )
                } else null
            }
            setupRecyclerView()
            setupYearSpinner()
        }
    }

    private fun setupRecyclerView() {
        achievementsAdapter = AchievementAdapter(originalAchievements)
        binding.recyclerViewAchievements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = achievementsAdapter
        }
    }

    private fun setupYearSpinner() {
        val years = originalAchievements.map { it.year }.distinct().sortedDescending()
        val yearOptions = listOf("All") + years.map { it.toString() }
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, yearOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerYear.adapter = spinnerAdapter
        binding.spinnerYear.setSelection(0)

        binding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedYear = yearOptions[position]
                filterAchievementsByYear(selectedYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                filterAchievementsByYear("All")
            }
        }
    }

    private fun filterAchievementsByYear(year: String) {
        val filteredList = if (year == "All") {
            originalAchievements
        } else {
            try {
                val yearInt = year.toInt()
                originalAchievements.filter { achievement ->
                    achievement.year == yearInt
                }
            } catch (e: NumberFormatException) {
                emptyList()
            }
        }
        achievementsAdapter.updateDisplayedItems(filteredList)
    }
}