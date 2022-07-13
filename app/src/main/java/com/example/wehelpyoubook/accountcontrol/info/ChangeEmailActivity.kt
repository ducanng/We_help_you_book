package com.example.wehelpyoubook.accountcontrol.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.wehelpyoubook.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangeEmailActivity : AppCompatActivity() {
    private var editEmail: TextInputEditText? = null
    private var saveBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        initChangeEmailUI()
        val email = editEmail!!.text.toString().trim()
        saveBtn!!.setOnClickListener {
            changeEmail(email)
        }
    }

    @Suppress("NAME_SHADOWING")
    private fun changeEmail(email: String) {
        val user = Firebase.auth.currentUser
        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Đã gửi email xác thực", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else  {
                    Toast.makeText(this, "Cập nhật email thất bại", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun initChangeEmailUI() {
        editEmail = findViewById(R.id.change_email_edittext)
        saveBtn = findViewById(R.id.update_email_button)
    }
}