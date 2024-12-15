package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.anmp_project.R
import com.example.anmp_project.databinding.FragmentApplyTeamSubmitBinding
import com.example.anmp_project.model.Apply
import com.example.anmp_project.viewmodel.ApplyTeamSubmitViewModel

class ApplyTeamSubmitFragment : Fragment() {

    private lateinit var binding: FragmentApplyTeamSubmitBinding
    private lateinit var viewModel: ApplyTeamSubmitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamSubmitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ApplyTeamSubmitViewModel::class.java)

        val spinnerGame: Spinner = view.findViewById(R.id.spinnerGame)
        val spinnerTeam: Spinner = view.findViewById(R.id.spinnerTeam)
        val editTextDescription: EditText = view.findViewById(R.id.editTextTextMultiLine3)
        val button: Button = view.findViewById(R.id.button)

        val gameList = listOf("Valorant", "Mobile Legends", "League of Legends", "Call of Duty", "DOTA 2", "Fortnite")
        val teamsMap = mapOf(
            "Valorant" to listOf("Valorant Team A", "Valorant Team B", "Valorant Team C"),
            "Mobile Legends" to listOf("Team A", "Team B", "Team C"),
            "League of Legends" to listOf("Team A", "Team B", "Team C"),
            "Call of Duty" to listOf("Team A", "Team B", "Team C"),
            "DOTA 2" to listOf("Team A", "Team B", "Team C"),
            "Fortnite" to listOf("Fortnite Team A", "Fortnite Team B", "Fortnite Team C")
        )

        val gameAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gameList)
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGame.adapter = gameAdapter

        spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>?) {}

            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedGame = gameList[position]
                val teamsForGame = teamsMap[selectedGame] ?: emptyList()

                val teamAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teamsForGame)
                teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerTeam.adapter = teamAdapter
            }
        }

        button.setOnClickListener {
            val selectedGame = spinnerGame.selectedItem.toString()
            val selectedTeam = spinnerTeam.selectedItem.toString()
            val description = editTextDescription.text.toString()

            val apply = Apply(
                game = selectedGame,
                team = selectedTeam,
                description = description,
                status = "WAITING"
            )

            viewModel.addApply(listOf(apply))

            findNavController().navigate(R.id.action_applyTeamSubmitFragment_to_applyTeamListFragment)

            // reset field
            spinnerGame.setSelection(0)
            spinnerTeam.setSelection(0)
            editTextDescription.text.clear()
        }
    }
}

