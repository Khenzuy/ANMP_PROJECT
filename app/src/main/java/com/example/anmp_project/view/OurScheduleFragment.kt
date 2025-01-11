//package com.example.anmp_project.view
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.anmp_project.R
//import com.example.anmp_project.model.Schedule
//import com.example.anmp_project.viewmodel.ScheduleViewModel
//import com.example.anmp_project.databinding.FragmentOurScheduleBinding
//
//class OurScheduleFragment : Fragment() {
//
//    private val scheduleViewModel: ScheduleViewModel by viewModels()
//    private lateinit var ourScheduleAdapter: OurScheduleAdapter
//    private var _binding: FragmentOurScheduleBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentOurScheduleBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        ourScheduleAdapter = OurScheduleAdapter(emptyList()) { schedule -> navigateToScheduleDetail(schedule)
//        }
//
//        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.scheduleRecyclerView.adapter = ourScheduleAdapter
//
//        scheduleViewModel.schedules.observe(viewLifecycleOwner) { schedules ->
//            schedules?.let {
//                ourScheduleAdapter = OurScheduleAdapter(it) { schedule ->
//                    navigateToScheduleDetail(schedule)
//                }
//                binding.scheduleRecyclerView.adapter = ourScheduleAdapter
//                binding.progressLoad.visibility = View.GONE
//                binding.scheduleRecyclerView.visibility = View.VISIBLE
//            }
//        }
//
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            binding.progressLoad.visibility = View.VISIBLE
//            binding.scheduleRecyclerView.visibility = View.GONE
//            scheduleViewModel.fetchSchedules()
//            binding.swipeRefreshLayout.isRefreshing = false
//        }
//        scheduleViewModel.fetchSchedules()
//    }
//
//    private fun navigateToScheduleDetail(schedule: Schedule) {
//        val bundle = Bundle().apply {
//            putString("event_name", schedule.event_name)
//            putString("esport_team", schedule.esport_team)
//            putString("event_description", schedule.event_description)
//            putString("image_url", schedule.event_photo)
//            putString("event location", schedule.venue)
//            putString("event_time", schedule.event_time)
//        }
//        findNavController().navigate(R.id.scheduleDetailFragment, bundle)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
