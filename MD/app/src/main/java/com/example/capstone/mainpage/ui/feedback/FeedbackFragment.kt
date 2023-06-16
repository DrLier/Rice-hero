package com.example.capstone.mainpage.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capstone.R
import com.example.capstone.databinding.FragmentFeedbackBinding
import com.example.capstone.model.Feedback
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null

    private val binding get() = _binding!!
    private lateinit var db : FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Firebase.database

        val feedbackRef = db.reference.child(FEEDBACK_CHILD)

        binding.buttonSubmitFeedback.setOnClickListener {
            val feedback = Feedback(
                binding.editTextFeedback.text.toString(),
                Date().time
            )

            feedbackRef.push().setValue(feedback) { error, _ ->
                if (error != null) {
                    Toast.makeText(requireContext(), getString(R.string.send_error) + error.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.send_success), Toast.LENGTH_SHORT).show()
                }
            }
            binding.editTextFeedback.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FEEDBACK_CHILD = "feedback"
    }
}