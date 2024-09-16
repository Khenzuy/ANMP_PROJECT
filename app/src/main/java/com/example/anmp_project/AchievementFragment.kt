package com.example.anmp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anmp_project.databinding.FragmentAchievementBinding
import com.example.anmp_project.databinding.FragmentOurScheduleBinding

class AchievementFragment : Fragment() {
    private lateinit var binding: FragmentAchievementBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val image = AchievementFragmentArgs.fromBundle(requireArguments()).image
            val nameGame = AchievementFragmentArgs.fromBundle(requireArguments()).nameGame
            binding.imageView.setImageResource(image)
            binding.txtGameName.text = nameGame
        }
        }

    }