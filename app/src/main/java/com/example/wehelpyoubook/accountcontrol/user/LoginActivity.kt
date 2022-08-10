package com.example.wehelpyoubook.accountcontrol.user

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
=======
>>>>>>> an
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
import android.content.Intent
import android.os.Bundle
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
import com.example.wehelpyoubook.homescreen.HomeActivity
=======
>>>>>>> an
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
<<<<<<< HEAD
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.EmailVerificationActivity
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private var setTv: TextView? = null
=======
<<<<<<< HEAD
=======
    private var setTv: TextView? = null
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
    private var setTv: TextView? = null
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
    private var editPass: TextInputEditText? = null
    private var btnCallLogin: Button? = null
    private var tvForgotPass: TextView? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
        setContentView(R.layout.activity_login)
        editPass = findViewById(R.id.login_password_edittext)
        setTv = findViewById(R.id.login_set_textview)
        btnCallLogin = findViewById<View>(R.id.login_button) as Button
        tvForgotPass = findViewById(R.id.forgot_password_textview)

        val intent = this.intent
        val email = intent.getStringExtra("email")
        auth = FirebaseAuth.getInstance()
<<<<<<< HEAD

<<<<<<< HEAD
=======
>>>>>>> an
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_login)
        editPass = findViewById(R.id.login_password_edittext)
        setTv = findViewById(R.id.login_set_textview)
        btnCallLogin = findViewById<View>(R.id.login_button) as Button
        tvForgotPass = findViewById(R.id.forgot_password_textview)


        val intent = this.intent
        val email = intent.getStringExtra("email")
        auth = FirebaseAuth.getInstance()
        setTv!!.text = "Đăng nhập với $email"
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        setTv!!.text = "Đăng nhập với $email"
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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
<<<<<<< HEAD
<<<<<<< HEAD
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
=======
<<<<<<< HEAD
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
=======
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
>>>>>>> main
>>>>>>> an
=======
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> an
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
}