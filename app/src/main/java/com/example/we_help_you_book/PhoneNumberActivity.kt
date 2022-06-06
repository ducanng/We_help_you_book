package com.example.we_help_you_book

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhoneNumberActivity : AppCompatActivity() {
    private var editPhone: EditText? = null
    private var btnPhone: Button? = null
    //var phoneNumber: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_phone_number)
        editPhone = findViewById(R.id.editTextPhoneNumber)
        btnPhone = findViewById(R.id.buttonCall)
        var str: String
        this@PhoneNumberActivity.btnPhone!!.setOnClickListener {
            str = this@PhoneNumberActivity.editPhone!!.text.toString()
            val msg = Toast.makeText(this@PhoneNumberActivity, str, Toast.LENGTH_LONG)
            msg.show()
        }

    }

}