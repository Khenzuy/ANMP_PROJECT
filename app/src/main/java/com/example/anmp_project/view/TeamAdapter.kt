package com.example.anmp_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemTeamBinding
import com.example.anmp_project.model.Team


class TeamAdapter(private var teamList: List<Team>, private val gamePhoto: String) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    class TeamViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamList[position]
        holder.binding.txtTeam.text = team.teamName

        holder.itemView.setOnClickListener {
            val action = TeamFragmentDirections.actionItemTeamToItemTeamDetail(
                team.teamName,
                gamePhoto
            )
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = teamList.size

    fun updateData(newTeams: List<Team>) {
        teamList = newTeams
        notifyDataSetChanged()
    }
}