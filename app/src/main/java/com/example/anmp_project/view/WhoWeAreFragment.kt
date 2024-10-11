package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anmp_project.databinding.FragmentWhoWeAreBinding

class WhoWeAreFragment : Fragment() {
    private lateinit var binding: FragmentWhoWeAreBinding
    private var likeCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhoWeAreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTeamDescription.text = "Our e-sports team, Daffa Gaming, is renowned for its incredible teamwork and dedication to achieving the highest standards of gameplay. With a diverse roster of skilled players, we participate in multiple gaming titles, including Valorant, Mobile Legends, Call of Duty, Fortnite, Dota 2 and League of Legends. Our team emphasizes strategic collaboration and constant improvement, ensuring that each player develops their unique strengths."
        binding.btnLike.setOnClickListener {
            likeCount++
            binding.btnLike.text = "$likeCount"
            //binding.btnLike.isEnabled = false kalo mau 1x klik disable
        }
    }
}
