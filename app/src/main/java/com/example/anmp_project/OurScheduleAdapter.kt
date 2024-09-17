package com.example.anmp_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.databinding.ItemScheduleBinding

class ScheduleAdapter(private val schedules: List<Schedule>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        val dateParts = schedule.date.split(" ")

        holder.binding.scheduleDate.text = dateParts[0]
        holder.binding.scheduleMonth.text = dateParts[1]
        holder.binding.scheduleTitle.text = schedule.title
        holder.binding.scheduleSubtitle.text = schedule.subtitle

        holder.itemView.setOnClickListener {
            val action = OurScheduleFragmentDirections.actionOurScheduleFragmentToScheduleDetailFragment(
                date = schedule.date,
                title = schedule.title,
                subtitle = schedule.subtitle
            )
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int = schedules.size
}
data class Schedule(
    val date: String,
    val title: String,
    val subtitle: String,
    val location: String,
    val description: String
)

