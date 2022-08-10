package com.example.wehelpyoubook.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// For Feedback

class FeedbackViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Feedback"
    }
    val text: LiveData<String> = _text

}