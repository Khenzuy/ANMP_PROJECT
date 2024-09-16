package com.example.anmp_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemCompetitionBinding

class CompetitionAdapter(private val competitions: List<Competition>) :
    RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>(){

    class CompetitionViewHolder(val binding: ItemCompetitionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val binding = ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val competition = competitions[position]

        holder.binding.competitionImage.setImageResource(competition.image)
        holder.binding.txtGame.text = competition.nameGame
        holder.binding.txtDescription.text = competition.description
        holder.binding.btnAchievement.setOnClickListener {
            val action = MainFragmentDirections.actionItemWhatWePlayToItemAchievement(
                competition.image,
                competition.nameGame
            )
            holder.itemView.findNavController().navigate(action)
        }
        holder.binding.btnTeam.setOnClickListener {
            val action = MainFragmentDirections.actionItemWhatWePlayToItemTeam()
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = competitions.size
}

data class Competition(val image: Int, val nameGame: String, val description: String)