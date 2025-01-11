//package com.example.anmp_project.view
//
//import android.app.AlertDialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.anmp_project.databinding.FragmentScheduleDetailBinding
//import com.squareup.picasso.Picasso
//
//class ScheduleDetailFragment : Fragment() {
//
//    private var _binding: FragmentScheduleDetailBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentScheduleDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.notifyButton.setOnClickListener {
//            showNotificationDialog()
//        }
//
//        val eventName = arguments?.getString("event_name")
//        val esportTeam = arguments?.getString("esport_team")
//        val eventDescription = arguments?.getString("event_description")
//        val eventLocation = arguments?.getString("event location")
//        val imageUrl = arguments?.getString("image_url")
//        val eventTime = requireArguments().getString("event_time")
//
//        binding.scheduleTitleTextView.text = eventName
//        binding.teamNameTextView.text = esportTeam
//        binding.scheduleDescriptionTextView.text = eventDescription
//        binding.locationTextView.text = eventLocation
//        binding.eventTimeTextView.text = eventTime
//
//        Picasso.get()
//            .load(imageUrl)
//            .placeholder(android.R.drawable.ic_menu_gallery)
//            .error(android.R.drawable.ic_menu_close_clear_cancel)
//            .into(binding.scheduleImageView)
//    }
//    private fun showNotificationDialog() {
//        AlertDialog.Builder(requireContext())
//            .setTitle("Notification")
//            .setMessage("Notification created.")
//            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
//            .create()
//            .show()
//    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
