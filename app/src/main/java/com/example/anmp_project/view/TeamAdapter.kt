//package com.example.anmp_project.view
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import com.example.anmp_project.databinding.ItemTeamBinding
//import com.example.anmp_project.model.TeamData
//
//
//
//class TeamAdapter(private val teamList: List<TeamData>,private val gamePhoto: String) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
//
//    class TeamViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
//        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return TeamViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
//        val team = teamList[position]
//        holder.binding.txtTeam.text = team.team_name
//
//        holder.itemView.setOnClickListener {
//            val action = TeamFragmentDirections.actionItemTeamToItemTeamDetail(team.team_name, gamePhoto)
//            it.findNavController().navigate(action)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return teamList.size
//    }
//}
