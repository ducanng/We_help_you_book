package com.example.wehelpyoubook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.databinding.ActivityFeedbackBinding

class TestActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
