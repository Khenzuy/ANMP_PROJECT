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
            .load(competition.gamePhoto)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .into(holder.binding.competitionImage)

        holder.binding.txtGame.text = competition.gameName
        holder.binding.txtDescription.text = competition.gameDescription

        // Assuming the navigation actions are correctly set up in nav_graph.xml
//        holder.binding.btnAchievement.setOnClickListener {
//            // This part of code needs proper setup in the navigation graph and argument handling
//            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemAchievement(competition.id)
//            holder.itemView.findNavController().navigate(action)
//        }
//
//        holder.binding.btnTeam.setOnClickListener {
//            // This part of code needs proper setup in the navigation graph and argument handling
//            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemTeam(competition.id)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    fun updateCompetitionList(newCompetitionList: List<Competition>) {
        competitions.clear()
        competitions.addAll(newCompetitionList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = competitions.size
}
