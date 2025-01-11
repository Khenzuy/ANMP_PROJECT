package com.example.anmp_project.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.anmp_project.R
import com.example.anmp_project.databinding.FragmentApplyTeamSubmitBinding
import com.example.anmp_project.model.Proposal
import com.example.anmp_project.viewmodel.ApplyTeamSubmitViewModel

class ApplyTeamSubmitFragment : Fragment() {

    private lateinit var binding: FragmentApplyTeamSubmitBinding
    private lateinit var viewModel: ApplyTeamSubmitViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApplyTeamSubmitBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApplyTeamSubmitViewModel::class.java)

        val spinnerGame = binding.spinnerGame
        val spinnerTeam = binding.spinnerTeam
        val editTextDescription = binding.editTextTextMultiLine3
        val buttonSubmit = binding.buttonSubmit

        viewModel.competitions.observe(viewLifecycleOwner) { competitions ->
            if (!competitions.isNullOrEmpty()) {
                val gameAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    competitions.map { it.gameName }
                )
                gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerGame.adapter = gameAdapter
            } else {
                Toast.makeText(requireContext(), "No competitions available", Toast.LENGTH_SHORT).show()
            }
        }

        spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parentView: AdapterView<*>) {}

            override fun onItemSelected(parentView: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGameName = spinnerGame.selectedItem.toString()
                viewModel.loadTeams(selectedGameName).observe(viewLifecycleOwner) { teams ->
                    if (teams != null && teams.isNotEmpty()) {
                        val teamAdapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            teams.map { it.teamName }
                        )
                        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerTeam.adapter = teamAdapter
                    } else {
                        spinnerTeam.adapter = null
                    }
                }
            }
        }

        buttonSubmit.setOnClickListener {
            val game = spinnerGame.selectedItem?.toString() ?: ""
            val team = spinnerTeam.selectedItem?.toString() ?: ""
            val description = editTextDescription.text.toString()
            val username = sharedPreferences.getString("username", null) ?: "UnknownUser"

            if (game.isEmpty() || team.isEmpty() || description.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else {
                val proposal = Proposal(
                    game = game,
                    team = team,
                    description = description,
                    status = "WAITING",
                    username = username
                )
                viewModel.submitProposal(proposal)
                Toast.makeText(requireContext(), "Proposal submitted successfully.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_applyTeamSubmitFragment_to_applyTeamListFragment)
            }
        }
    }
}
