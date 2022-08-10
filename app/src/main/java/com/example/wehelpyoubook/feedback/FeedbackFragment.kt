package com.example.wehelpyoubook.feedback

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
=======
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import com.example.wehelpyoubook.databinding.FragmentFeedbackBinding

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
<<<<<<< HEAD

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        startActivity(Intent(activity, Feedback::class.java))
=======
        val feedbackViewModel =
            ViewModelProvider(this).get(FeedbackViewModel::class.java)

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root

>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}