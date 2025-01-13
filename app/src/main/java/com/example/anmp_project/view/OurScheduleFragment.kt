package com.example.anmp_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project.databinding.FragmentOurScheduleBinding
import com.example.anmp_project.viewmodel.ScheduleViewModel

class OurScheduleFragment : Fragment() {

    private var _binding: FragmentOurScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScheduleViewModel
    private val scheduleAdapter = OurScheduleAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOurScheduleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.scheduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = scheduleAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.schedulesLD.observe(viewLifecycleOwner) { schedules ->
            schedules?.let {
                scheduleAdapter.updateScheduleList(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorState.observe(viewLifecycleOwner) { isError ->
            binding.txtError.visibility = if (isError) View.VISIBLE else View.GONE
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
