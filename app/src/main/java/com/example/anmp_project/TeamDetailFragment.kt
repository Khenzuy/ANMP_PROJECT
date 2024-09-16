package com.example.anmp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anmp_project.databinding.FragmentTeamDetailBinding
import com.example.anmp_project.databinding.FragmentWhoWeAreBinding

class TeamDetailFragment : Fragment(){
    private lateinit var binding: FragmentTeamDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


}