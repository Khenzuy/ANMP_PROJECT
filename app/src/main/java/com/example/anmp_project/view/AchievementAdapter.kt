package com.example.anmp_project.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemAchievementBinding
import com.example.anmp_project.model.Achievement
import com.example.anmp_project.model.TeamAchievement

class AchievementAdapter(private val originalItems: List<Achievement>) :
    RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {

    private var displayedItems: List<Achievement> = originalItems

    class AchievementViewHolder(val binding: ItemAchievementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = ItemAchievementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        val achievement = displayedItems[position]
        val numberedAchievement = "${position + 1}. ${achievement.achievement} - ${achievement.teamName} (${achievement.year})"
        holder.binding.txtAchievement.text = numberedAchievement

    }


    override fun getItemCount(): Int {
        return displayedItems.size
    }

    fun updateDisplayedItems(newItems: List<Achievement>) {
        displayedItems = newItems
        notifyDataSetChanged()

    }

}