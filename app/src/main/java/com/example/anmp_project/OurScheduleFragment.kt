package com.example.anmp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anmp_project.databinding.FragmentOurScheduleBinding

class OurScheduleFragment : Fragment() {
    private lateinit var binding:FragmentOurScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOurScheduleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}