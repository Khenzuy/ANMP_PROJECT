//package com.example.anmp_project.view
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.anmp_project.databinding.ItemAchievementBinding
//import com.example.anmp_project.model.TeamAchievement
//
//class AchievementAdapter(private val originalItems: List<TeamAchievement>) :
//    RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {
//
//    private var displayedItems: List<TeamAchievement> = originalItems
//
//    class AchievementViewHolder(val binding: ItemAchievementBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
//        val binding = ItemAchievementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return AchievementViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
//        val achievement = displayedItems[position]
//        val numberedAchievement = "${position + 1}. ${achievement.achievement} - ${achievement.team_name} (${achievement.year})"
//        holder.binding.txtAchievement.text = numberedAchievement
//    }
//
//    override fun getItemCount(): Int = displayedItems.size
//
//    fun updateDisplayedItems(newItems: List<TeamAchievement>) {
//        displayedItems = newItems
//        notifyDataSetChanged()
//    }
//}