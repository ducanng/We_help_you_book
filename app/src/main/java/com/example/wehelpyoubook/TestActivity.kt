package com.example.wehelpyoubook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.databinding.ActivityDetailRestoBinding
import com.example.wehelpyoubook.databinding.ActivityTestBinding

class TestActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailRestoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
