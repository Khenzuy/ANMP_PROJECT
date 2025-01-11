//package com.example.anmp_project.view
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.anmp_project.databinding.ItemScheduleBinding
//import com.example.anmp_project.model.Schedule
//
//class OurScheduleAdapter(private val scheduleList: List<Schedule>, private val onItemClick: (Schedule) -> Unit
//) : RecyclerView.Adapter<OurScheduleAdapter.ScheduleViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
//        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ScheduleViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
//        val schedule = scheduleList[position]
//        holder.bind(schedule)
//        holder.itemView.setOnClickListener { onItemClick(schedule) }
//    }
//
//    override fun getItemCount(): Int = scheduleList.size
//
//    inner class ScheduleViewHolder(private val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(schedule: Schedule) {
//            binding.scheduleDate.text = schedule.date.day.toString()
//            binding.scheduleMonth.text = schedule.date.month.substring(0, 3)
//            binding.scheduleTitle.text = schedule.event_name
//            binding.scheduleSubtitle.text = schedule.esport_team
//        }
//    }
//}
