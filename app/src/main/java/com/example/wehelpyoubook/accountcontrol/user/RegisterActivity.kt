package com.example.wehelpyoubook.accountcontrol.user

import android.annotation.SuppressLint
<<<<<<< HEAD
=======
<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
=======
>>>>>>> an
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
=======
<<<<<<< HEAD
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


=======
>>>>>>> an
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var btnCallRegister: Button? = null
    private var editName: TextInputEditText? = null
    private var editPass: TextInputEditText? = null
    private var editRepass: TextInputEditText? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
<<<<<<< HEAD
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
=======
<<<<<<< HEAD
=======
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
>>>>>>> main
>>>>>>> an

        auth = FirebaseAuth.getInstance()
        val intent = this.intent
        val email =  intent.getStringExtra("email").toString()
        findViewById<TextView>(R.id.set_textview).text = "Đăng kí tài khoản với $email"

        editName = findViewById(R.id.fullname_edittext)
        editPass = findViewById(R.id.register_password_edittext)
        editRepass = findViewById(R.id.register_repassword_edittext)
        btnCallRegister = findViewById(R.id.register_button)
        btnCallRegister!!.setOnClickListener {
            val name = this.editName!!.text.toString()
            val pass = this.editPass!!.text.toString()
            val repass = this.editRepass!!.text.toString()
            if (isValidateName(name) && isValidatePassword(pass, editPass!!)) {
                if (repass.isEmpty()) {
                    editRepass!!.error = "Không thể để trống"
                } else {
                    if (pass != repass) {
                        editRepass!!.error = "Mật khẩu không khớp"
                        Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        createAccount(email, pass)
                    }
                }
            }
        }
    }

    private fun isValidatePassword(passwordInput: String, notification: TextInputEditText): Boolean {
        // if password field is empty
        // it will display error message "Field can not be empty"
        return if (passwordInput.isEmpty()) {
            notification.error = "Không thể để trống"
            false
        } else if (passwordInput.length < 6) {
            notification.error = "Mật khẩu yếu"
            false
        } else {
            notification.error = null
            true
        }
    }
    private fun isValidateName(name: String): Boolean {
        if (name == "") {
            editName!!.error = "Không thể để trống"
            return false
        }
        if (name.length < 4) {
            editName!!.error = "Đặt đầy đủ họ tên"
            return false
        }
        return true
    }
    @Suppress("NAME_SHADOWING")
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = editName!!.text.toString()
                    }
                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Đã cập nhật tên", Toast.LENGTH_SHORT)
                                    .show()
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> an
                                val us = User(
                                    user.uid,
                                    "https://images.foody.vn/default/s50/user-default-female.png",
                                    user.displayName,
                                    "customer",
                                    user.email,
                                    user.email,
                                    password,
                                    "",
                                    ""
                                )
                                db.collection("Users")
                                    .add(
                                        us
                                    )
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(TAG, "User added with ID: ${documentReference.id}")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(TAG, "Error adding User", e)
                                    }
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
                            }
                        }
                    val intent = Intent(this, EmailVerificationActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Đăng kí thất bại",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> an
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
}