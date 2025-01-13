package com.example.anmp_project.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemWhatWePlayBinding
import com.example.anmp_project.model.Achievement
import com.example.anmp_project.model.Competition
import com.example.anmp_project.model.EsportsDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val achievements = fetchAchievementsForCompetition(holder.binding.root.context, competition.id)

                val achievementStrings = achievements.map { achievement ->
                    "${achievement.teamName};${achievement.achievement};${achievement.year}"
                }.toTypedArray()

                holder.binding.btnAchievement.setOnClickListener {
                    val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemAchievement(
                        competition.gameName,
                        competition.gamePhoto,
                        achievementStrings
                    )
                    holder.itemView.findNavController().navigate(action)
                }
            } catch (e: Exception) {
                // Handle any errors if the database query fails
                e.printStackTrace()
            }
        }

        holder.binding.btnTeam.setOnClickListener {
            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemTeam(
                competitionId = competition.id,
                gamePhoto = competition.gamePhoto,
                gameName = competition.gameName
            )
            holder.itemView.findNavController().navigate(action)
        }

        // Fetch achievements related to this competition
//        val achievements = fetchAchievementsForCompetition(holder.binding.root.context, competition.id)
//        val achievementStrings = achievements.map { achievement ->
//            "${achievement.teamName};${achievement.achievement};${achievement.year}"
//        }.toTypedArray()
//
//        holder.binding.btnAchievement.setOnClickListener {
//            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemAchievement(
//                competition.gameName,
//                competition.gamePhoto,
//                achievementStrings
//            )
//            holder.itemView.findNavController().navigate(action)
//        }

//        holder.binding.btnTeam.setOnClickListener {
//            val action = WhatWePlayFragmentDirections.actionItemWhatWePlayToItemTeam(competition.id)
//            holder.itemView.findNavController().navigate(action)
//        }

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

    private suspend fun fetchAchievementsForCompetition(context: Context, competitionId: Int): List<Achievement> {
        // database opertaion pake background thread
        return withContext(Dispatchers.IO) {
            val database = EsportsDatabase.getDatabase(context = context, scope = CoroutineScope(Dispatchers.IO))
            database.achievementDao().getAchievementsForCompetition(competitionId)
        }
    }

    fun updateCompetitionList(newCompetitionList: List<Competition>) {
        competitions.clear()
        competitions.addAll(newCompetitionList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = competitions.size
}
