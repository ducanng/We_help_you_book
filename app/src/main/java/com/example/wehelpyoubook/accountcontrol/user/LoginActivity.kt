package com.example.wehelpyoubook.accountcontrol.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private var setTv: TextView? = null
    private var editPass: TextInputEditText? = null
    private var btnCallLogin: Button? = null
    private var tvForgotPass: TextView? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editPass = findViewById(R.id.login_password_edittext)
        setTv = findViewById(R.id.login_set_textview)
        btnCallLogin = findViewById<View>(R.id.login_button) as Button
        tvForgotPass = findViewById(R.id.forgot_password_textview)

        val intent = this.intent
        val email = intent.getStringExtra("email")
        auth = FirebaseAuth.getInstance()
        setTv!!.text = "Đăng nhập với $email"
        tvForgotPass!!.setOnClickListener {
            forgotPassword(email)
        }

        btnCallLogin!!.setOnClickListener {
            val pass = this@LoginActivity.editPass!!.text.toString()
            signIn(email.toString(), pass)
        }
    }

    private fun forgotPassword(email: String?) {
        Firebase.auth.sendPasswordResetEmail(email.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Đã gửi email đặt lại mật khẩu " +
                            "hãy kiểm tra thùng thư của $email", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@LoginActivity, "Gửi email thất bại!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if (user != null) {
                        if (user.isEmailVerified) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this@LoginActivity, EmailVerificationActivity::class.java))
                            Toast.makeText(this@LoginActivity, "email chưa xác nhận", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    editPass!!.error = "Mật khẩu chưa chính xác"
                    Toast.makeText(this@LoginActivity, "Đăng nhập thất bại", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        // [END sign_in_with_email]
    }
}