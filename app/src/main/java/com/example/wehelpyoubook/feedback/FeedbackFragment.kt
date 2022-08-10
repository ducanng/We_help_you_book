package com.example.wehelpyoubook.feedback

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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
import com.example.wehelpyoubook.mybooking.MyBookingFragment

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

<<<<<<< HEAD
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root

>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544

        return root
=======

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        startActivity(Intent(getActivity(), Feedback::class.java))
        return binding.root
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}