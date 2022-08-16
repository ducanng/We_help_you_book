package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.mybooking.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DeleteAccountActivity : AppCompatActivity() {
    private var cancelBtn: Button? = null
    private var deleteBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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
        db.collection("Users")
            .whereEqualTo("id", user.uid)
            .get()
            .addOnSuccessListener { documentSnap ->
                if(documentSnap.documents.isNotEmpty()) {
                    val documentRemoveId = documentSnap.documents[0].id
                    db.collection("Users").document(documentRemoveId).delete()
                }
            }
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Xóa tài khoản thành công", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, HomeSignInActivity::class.java))
                }
            }
    }

    private fun initDeleteAccountUI() {
        cancelBtn = findViewById(R.id.cancel_and_back_button)
        deleteBtn = findViewById(R.id.delete_account_button)
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@DeleteAccountActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}