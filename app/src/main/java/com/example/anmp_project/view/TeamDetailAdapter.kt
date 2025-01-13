package com.example.anmp_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.R
import com.example.anmp_project.databinding.ItemTeamDetailBinding
import com.example.anmp_project.model.Member
import com.squareup.picasso.Picasso

class TeamDetailAdapter(private var teamDetails: List<Member>) : RecyclerView.Adapter<TeamDetailAdapter.TeamDetailViewHolder>() {

    class TeamDetailViewHolder(val binding: ItemTeamDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailViewHolder {
        val binding = ItemTeamDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamDetailViewHolder, position: Int) {
        val team = teamDetails[position]
        holder.binding.txtPlayerName.text = team.memberName
        holder.binding.txtRole.text = team.memberRole

        Picasso.get().load(team.memberImage).
        placeholder(R.drawable.baseline_people_24).
        into(holder.binding.imagePlayer)
    }

    override fun getItemCount(): Int = teamDetails.size

    fun updateData(newMembers: List<Member>) {
        teamDetails = newMembers
        notifyDataSetChanged()
    }
}
