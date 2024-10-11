package com.example.anmp_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.appcompat.app.AlertDialog
import com.example.anmp_project.R


class ScheduleDetailFragment : Fragment() {

    private val args: ScheduleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule_detail, container, false)

        val date = args.date
        val title = args.title
        val subtitle = args.subtitle

        view.findViewById<TextView>(R.id.titleTextView).text = title
        view.findViewById<TextView>(R.id.locationTextView).text = date
        view.findViewById<TextView>(R.id.teamTextView).text = subtitle

        view.findViewById<TextView>(R.id.descriptionTextView).text = "This high-stakes event will bring together top teams from across the region, all competing for a chance to advance to the national finals. Expect intense gameplay, strategic plays, and thrilling moments as teams battle it out in one of the most popular first-person shooters. Fans can anticipate an action-packed day filled with memorable highlights and fierce competition in the heart of the esports scene."

        val notifyButton = view.findViewById<Button>(R.id.notifyButton)
        notifyButton.setOnClickListener {
            showNotificationDialog()
        }
        return view
    }

    private fun showNotificationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Notification created.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}
