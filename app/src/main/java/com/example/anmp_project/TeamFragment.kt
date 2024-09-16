package com.example.anmp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.anmp_project.databinding.FragmentAchievementBinding
import com.example.anmp_project.databinding.FragmentOurScheduleBinding
import com.example.anmp_project.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {
    private lateinit var binding: FragmentTeamBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.teamName.setOnClickListener {
            val action = TeamFragmentDirections.actionItemTeamToItemTeamDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

}