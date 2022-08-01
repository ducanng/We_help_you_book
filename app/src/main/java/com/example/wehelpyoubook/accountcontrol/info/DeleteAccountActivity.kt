package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DeleteAccountActivity : AppCompatActivity() {
    private var cancelBtn: Button? = null
    private var deleteBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)
        initDeleteAccountUI()
        cancelBtn!!.setOnClickListener {
            startActivity(Intent(this, UserInformationActivity::class.java))
        }
        deleteBtn!!.setOnClickListener {
            deleteAccount()
        }
    }

    private fun deleteAccount() {
        val user = Firebase.auth.currentUser!!
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Xóa tài khoản thành công", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
    }

    private fun initDeleteAccountUI() {
        cancelBtn = findViewById(R.id.cancel_and_back_button)
        deleteBtn = findViewById(R.id.delete_account_button)
    }
}