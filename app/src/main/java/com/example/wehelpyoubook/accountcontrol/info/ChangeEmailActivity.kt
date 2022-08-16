package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.mybooking.db
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class ChangeEmailActivity : AppCompatActivity() {
    private var editEmail: TextInputEditText? = null
    private var saveBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initChangeEmailUI()
        saveBtn!!.setOnClickListener {
            val email = editEmail!!.text.toString().trim()
            changeEmail(email)
        }
    }

    @Suppress("NAME_SHADOWING")
    private fun changeEmail(email: String) {
        val user = Firebase.auth.currentUser
        val id = user!!.uid
        db.collection("Users")
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { documentSnap ->
                if(documentSnap.documents.isNotEmpty()) {
                    val curUser = documentSnap.toObjects<User>()[0]

                    val documentRemoveId = documentSnap.documents[0].id
                    db.collection("Users").document(documentRemoveId).delete()
                    db.collection("Users").add(
                        User(
                            curUser.Id,
                            "https://images.foody.vn/default/s50/user-default-female.png",
                            curUser.name,
                            curUser.role,
                            email,
                            email,
                            curUser.password,
                            "",
                            curUser.role
                        )
                    )
                }
            }

        user.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Đã gửi email xác thực", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
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

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeEmailActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}
