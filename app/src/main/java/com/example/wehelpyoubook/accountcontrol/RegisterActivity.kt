package com.example.wehelpyoubook.accountcontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.homescreen.HomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var btnCallRegister: Button? = null
    private var editName: EditText? = null
    private var editPhone: EditText? = null
    private var editPass: TextInputEditText? = null
    private var editRepass: TextInputEditText? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        val intent = this.intent
        val email =  intent.getStringExtra("email").toString()
        findViewById<TextView>(R.id.set_textview).text = "Đăng kí tài khoản với $email"

        editName = findViewById(R.id.fullname_edittext)
        editPhone = findViewById(R.id.phone_edittext)

        editPass = findViewById(R.id.register_password_edittext)
        editRepass = findViewById(R.id.register_repassword_edittext)
        btnCallRegister = findViewById<View>(R.id.register_button) as Button
        btnCallRegister!!.setOnClickListener {
            val pass = this@RegisterActivity.editPass!!.text.toString()
            val repass = this@RegisterActivity.editRepass!!.text.toString()
            if (pass != repass) {
                Toast.makeText(this@RegisterActivity, "Mật khẩu không giống nhau", Toast.LENGTH_SHORT)
                    .show()
            } else {
                createAccount(email, pass)
            }
        }
    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
                    finishAffinity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }
}