package com.example.anmp_project.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.anmp_project.databinding.FragmentWhoWeAreBinding
import com.example.anmp_project.viewmodel.WhoWeAreViewModel
import com.squareup.picasso.Picasso

class WhoWeAreFragment : Fragment() {
    private lateinit var binding: FragmentWhoWeAreBinding
    private lateinit var viewModel: WhoWeAreViewModel
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhoWeAreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WhoWeAreViewModel::class.java)

        val sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", null) ?: ""

        if (username.isNotEmpty()) {
            viewModel.getUserData(username).observe(viewLifecycleOwner) { user ->
                user?.let {
                    binding.txtTeamDescription.text = it.teamDescription
                    binding.btnLike.text = it.likeCount.toString()

                    Picasso.get()
                        .load(it.profileImage)
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .error(android.R.drawable.ic_menu_close_clear_cancel)
                        .into(binding.imageView)
                }
            }

            binding.btnLike.setOnClickListener {
                viewModel.incrementLike(username)
            }
        } else {
            binding.txtTeamDescription.text = "Error: User not logged in."
        }
    }
}
