package com.example.wehelpyoubook.accountcontrol.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class EmailActivity : AppCompatActivity() {
    private var editEmail: TextInputEditText? = null
    private var btnContinue: Button? = null
    private lateinit var auth:FirebaseAuth

    @SuppressLint("ShowToast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_input_email)
        auth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.email_edittext)
        btnContinue = findViewById(R.id.continue_button)
        this@EmailActivity.btnContinue!!.setOnClickListener {
            val strEmail: String = this@EmailActivity.editEmail!!.text.toString().trim()
            if (isValidEmail(strEmail)) {
                Toast.makeText(this@EmailActivity, strEmail, Toast.LENGTH_SHORT)
                    .show()
                auth.fetchSignInMethodsForEmail(strEmail).addOnCompleteListener { task ->
                    val isNewUser = task.result.signInMethods?.isEmpty()
                    if (isNewUser == true) {
                        Toast.makeText(this@EmailActivity, "Register", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@EmailActivity, RegisterActivity::class.java)
                        intent.putExtra("email", strEmail)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@EmailActivity, "Login", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@EmailActivity, LoginActivity::class.java)
                        intent.putExtra("email", strEmail)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                editEmail!!.error = "không hợp lệ"
            }
        }
    }
    private fun isValidEmail(email: String): Boolean {
        if (email == "") {
            return false
        }
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@EmailActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}