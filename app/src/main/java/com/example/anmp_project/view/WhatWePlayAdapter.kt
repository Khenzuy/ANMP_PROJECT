package com.example.anmp_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemWhatWePlayBinding
import com.example.anmp_project.model.Competition
import com.squareup.picasso.Picasso

class WhatWePlayAdapter(private val competitions: ArrayList<Competition>) :
    RecyclerView.Adapter<WhatWePlayAdapter.WhatWePlayViewHolder>() {

    class WhatWePlayViewHolder(val binding: ItemWhatWePlayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatWePlayViewHolder {
        val binding = ItemWhatWePlayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WhatWePlayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WhatWePlayViewHolder, position: Int) {
        val competition = competitions[position]

        Picasso.get()
            .load(competition.game_photo)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .into(holder.binding.competitionImage)

        holder.binding.txtGame.text = competition.game_name
        holder.binding.txtDescription.text = competition.game_description
        holder.binding.btnAchievement.setOnClickListener {
            val achievementsAsStrings = competition.team_achievements.map {
                "${it.achievement};${it.team_name};${it.year}"
            }.toTypedArray()
            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemAchievement(
                competition.game_photo,
                competition.game_name,
                achievementsAsStrings
            )
            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.btnTeam.setOnClickListener {
            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemTeam()
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun updateCompetitionList(newCompetitionList: ArrayList<Competition>) {
        competitions.clear()
        competitions.addAll(newCompetitionList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = competitions.size
}
