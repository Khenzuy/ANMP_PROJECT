//package com.example.anmp_project.view
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.anmp_project.databinding.FragmentApplyTeamListBinding
//import com.example.anmp_project.viewmodel.ApplyTeamListViewModel
//
//
//class ApplyTeamListFragment : Fragment() {
//
//    private lateinit var viewModel: ApplyTeamListViewModel
//    private val applyListAdapter = ApplyTeamListAdapter(arrayListOf())
//    private lateinit var binding: FragmentApplyTeamListBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentApplyTeamListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel = ViewModelProvider(this).get(ApplyTeamListViewModel::class.java)
//        viewModel.refresh()
//        binding.proposalRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.proposalRecyclerView.adapter = applyListAdapter
//
//        observeViewModel()
//
//        binding.floatingActionButton.setOnClickListener {
//            val action = ApplyTeamListFragmentDirections.actionApplyTeamListFragmentToApplyTeamSubmitFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//    }
//
//    fun observeViewModel() {
//        viewModel.applyLD.observe(viewLifecycleOwner, Observer {
//            applyListAdapter.updateApplyList(it)
//            if (it.isEmpty()) {
//                binding.proposalRecyclerView?.visibility = View.GONE
//            } else {
//                binding.proposalRecyclerView?.visibility = View.VISIBLE
//            }
//        })
//    }
//}
//
