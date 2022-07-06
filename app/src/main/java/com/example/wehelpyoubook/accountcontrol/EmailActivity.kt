package com.example.wehelpyoubook.accountcontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class EmailActivity : AppCompatActivity() {
    private var editEmail: EditText? = null
    private var btnContinue: Button? = null
    private lateinit var checkEmail: Button
    private lateinit var auth:FirebaseAuth

    @SuppressLint("ShowToast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_email)
        auth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.email_edittext)
        checkEmail = findViewById(R.id.check_email)
        btnContinue = findViewById(R.id.continue_button)
        var flag = false
        checkEmail.setOnClickListener {
            val strEmail: String = this@EmailActivity.editEmail!!.text.toString()
            if (isValidEmail(strEmail)) {
                checkEmail.text = "email is correct"
                flag = true
            } else {
                checkEmail.text = "invalid syntax"
            }
        }
        this@EmailActivity.btnContinue!!.setOnClickListener {
            if (flag) {
                val strEmail: String = this@EmailActivity.editEmail!!.text.toString()
                if (strEmail == "") {
                    Toast.makeText(this@EmailActivity, "email is null", Toast.LENGTH_SHORT)
                        .show()
                }
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

            }
        }
    }
    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}