package com.example.wehelpyoubook.accountcontrol

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R

class HomeSignInActivity : AppCompatActivity() {
    private var btnCallInputPhoneActivity: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)

        btnCallInputPhoneActivity = findViewById<View>(R.id.phone_number_button) as Button
        btnCallInputPhoneActivity!!.setOnClickListener {
            val intent = Intent(this@HomeSignInActivity, PhoneNumberActivity::class.java)
            startActivity(intent)
        }
    }
}