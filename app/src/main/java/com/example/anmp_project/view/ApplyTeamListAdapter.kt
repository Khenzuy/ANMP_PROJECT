package com.example.anmp_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemApplyTeamListBinding
import com.example.anmp_project.model.Apply

class ApplyTeamListAdapter(val applyList: ArrayList<Apply>) :
    RecyclerView.Adapter<ApplyTeamListAdapter.ApplyViewHolder>() {

    class ApplyViewHolder(var binding: ItemApplyTeamListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val binding = ItemApplyTeamListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ApplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        val apply = applyList[position]
        holder.binding.textApplication.text = "${apply.game} - ${apply.team}"
        holder.binding.textStatus.text = apply.status // Set the status text
    }

    override fun getItemCount(): Int {
        return applyList.size
    }

    fun updateApplyList(newApplyList: List<Apply>) {
        applyList.clear()
        applyList.addAll(newApplyList)
        notifyDataSetChanged()
    }
}
