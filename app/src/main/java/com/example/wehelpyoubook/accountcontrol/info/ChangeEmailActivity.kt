package com.example.wehelpyoubook.accountcontrol.info

<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Intent
=======
<<<<<<< HEAD
=======
import android.content.Intent
>>>>>>> main
>>>>>>> an
=======
import android.content.Intent
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.wehelpyoubook.MainActivity
=======
<<<<<<< HEAD
=======
import com.example.wehelpyoubook.MainActivity
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
        initChangeEmailUI()
        val email = editEmail!!.text.toString().trim()
        saveBtn!!.setOnClickListener {
=======
>>>>>>> an
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initChangeEmailUI()
        saveBtn!!.setOnClickListener {
            val email = editEmail!!.text.toString().trim()
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
        initChangeEmailUI()
        saveBtn!!.setOnClickListener {
            val email = editEmail!!.text.toString().trim()
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
<<<<<<< HEAD
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
=======
<<<<<<< HEAD
=======
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
>>>>>>> main
>>>>>>> an
=======
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
}
=======
>>>>>>> an

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeEmailActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
}
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
