package com.example.wehelpyoubook.accountcontrol.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EmailVerificationActivity : AppCompatActivity() {
    private var verifyBtn: Button? = null
    private var emailVerifyTv: TextView? = null
    private var continueBtn: Button? = null
    private lateinit var auth: FirebaseAuth
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_email_verification)
        init()

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email
        emailVerifyTv!!.text = "Vui lòng xác nhận email\n$email"

        verifyBtn!!.setOnClickListener {
            sendEmailVerification(user)
        }
        continueBtn!!.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@EmailVerificationActivity,MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
    private fun init() {
        verifyBtn = findViewById(R.id.verify_button)
        emailVerifyTv = findViewById(R.id.sendVerify)
        continueBtn = findViewById(R.id.continue_button)
    }
    private fun sendEmailVerification(user: FirebaseUser?) {
        // [START send_email_verification]
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Đã gửi email", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        // [END send_email_verification]
    }
}