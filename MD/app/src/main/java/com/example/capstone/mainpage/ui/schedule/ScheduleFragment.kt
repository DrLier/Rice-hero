package com.example.capstone.mainpage.ui.schedule

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.FragmentScheduleBinding
import com.example.capstone.helper.ScheduleFactory
import com.example.capstone.model.Planting
import java.util.Date

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!
    private lateinit var scheduleViewModel: ScheduleViewModel
    private lateinit var builder : AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory : ScheduleFactory = ScheduleFactory.getInstance(requireContext())
        scheduleViewModel = ViewModelProvider(requireActivity(), factory)[ScheduleViewModel::class.java]
        builder = AlertDialog.Builder(requireContext())

        binding.btnAdd.setOnClickListener {
            builder.setTitle("Confirmation")
                .setMessage("Do you want to plant right now?")
                .setCancelable(true)
                .setPositiveButton("Yes") { dialogInterface, _ ->
                    scheduleViewModel.insertPlanting(Planting(0, System.currentTimeMillis()))
                    dialogInterface.dismiss()
                }
                .setNegativeButton("No") {dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .show()
            binding.btnAdd.visibility = View.GONE
        }

        scheduleViewModel.getPlanting().observe(requireActivity(), {
            if (it != null) {
                binding.cardViewPlanting.visibility = View.VISIBLE
                binding.btnAdd.visibility = View.GONE
                val date = System.currentTimeMillis()
                val dateReal = Date(date)
                val date2 = it.timestamp
                val dateDatabase = Date(date2)

                val diff = (dateReal.time - dateDatabase.time)/1000/60/60/24
                scheduleViewModel.getDailyRecommendation(diff.hashCode()).observe(requireActivity(), {
                    binding.dayOfPlant.text = "Day : $diff"
                    binding.recommendation.text = "Rekomendasi : ${it.recommendation}"
                })
            }
        })
    }
}