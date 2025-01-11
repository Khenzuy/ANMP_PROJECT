package com.example.anmp_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemApplyTeamListBinding
import com.example.anmp_project.model.Proposal

class ApplyTeamListAdapter(private var proposals: List<Proposal>) : RecyclerView.Adapter<ApplyTeamListAdapter.ApplyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val binding = ItemApplyTeamListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        val proposal = proposals[position]
        holder.binding.textApplication.text = "${proposal.game} - ${proposal.team}"
        holder.binding.textStatus.text = proposal.status
    }

    override fun getItemCount(): Int = proposals.size

    fun updateProposals(newProposals: List<Proposal>) {
        proposals = newProposals
        notifyDataSetChanged()
    }

    class ApplyViewHolder(val binding: ItemApplyTeamListBinding) : RecyclerView.ViewHolder(binding.root)
}
