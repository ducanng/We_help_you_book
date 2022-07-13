package com.example.wehelpyoubook.accountcontrol.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.info.ChangeEmailActivity
import com.example.wehelpyoubook.accountcontrol.info.ChangePasswordActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ReAuthenticateActivity : AppCompatActivity() {
    private var confirm: Button? = null
    private var textInputLayout: TextInputLayout? = null
    private var email: TextInputEditText? = null
    private var password: TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_authenticate)
        initReAuthenticateUI()
        val user = Firebase.auth.currentUser
        textInputLayout!!.editText!!.setText(user!!.email.toString())
        confirm!!.setOnClickListener {
            reAuthenticateAUser(password!!.text.toString())
        }
    }

    private fun initReAuthenticateUI() {
        confirm = findViewById(R.id.confirm_button)
        textInputLayout = findViewById(R.id.textInputLayout_email)
        email = findViewById(R.id.confirm_email_edittext)
        password = findViewById(R.id.confirm_password_edittext)
    }

    private fun reAuthenticateAUser(password: String) {
        val user = Firebase.auth.currentUser!!

        val credential = EmailAuthProvider.getCredential(user.email!!.toString(), password)
        val intent = this.intent
        val change = intent.getStringExtra("change_email").toString()
        user.reauthenticate(credential)
            .addOnCompleteListener {
                Toast.makeText(this, "Xác nhận thành công", Toast.LENGTH_SHORT).show()
                if (change == "email") {
                    startActivity(Intent(this, ChangeEmailActivity::class.java))
                }
            }
    }
}