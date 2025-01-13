package com.example.anmp_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project.R
import com.example.anmp_project.databinding.ItemScheduleBinding
import com.example.anmp_project.model.Schedule

class OurScheduleAdapter(private var scheduleList: List<Schedule>) :
    RecyclerView.Adapter<OurScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = scheduleList[position]

        holder.binding.apply {
            scheduleDate.text = schedule.day.toString()
            scheduleMonth.text = schedule.month
            scheduleTitle.text = schedule.eventName
            scheduleSubtitle.text = schedule.esportTeam
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("eventName", schedule.eventName)
                putInt("day", schedule.day)
                putString("month", schedule.month)
                putInt("year", schedule.year)
                putString("esportTeam", schedule.esportTeam)
                putString("eventPhoto", schedule.eventPhoto)
                putString("eventTime", schedule.eventTime)
                putString("venue", schedule.venue)
                putString("eventDescription", schedule.eventDescription)
            }
            it.findNavController().navigate(R.id.action_ourScheduleFragment_to_scheduleDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int = scheduleList.size

    fun updateScheduleList(newScheduleList: List<Schedule>) {
        scheduleList = newScheduleList.toMutableList()
        notifyDataSetChanged()
    }
}
