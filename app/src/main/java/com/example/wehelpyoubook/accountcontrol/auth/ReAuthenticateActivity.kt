package com.example.wehelpyoubook.accountcontrol.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.accountcontrol.info.DeleteAccountActivity
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
    private var tvConfirm: TextView? = null
    private var textInputLayout: TextInputLayout? = null
    private var email: TextInputEditText? = null
    private var password: TextInputEditText? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_authenticate)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initReAuthenticateUI()
        val intent = this.intent
        val change = intent.getStringExtra("choose")!!.toString()
        val choose = intent.getIntExtra("pos", -1)
        if (change == "c_email") {
            tvConfirm!!.text = "Xác nhận thay đổi email"
        }
        if (change == "c_password") {
            tvConfirm!!.text = "Xác nhận thay đổi mật khẩu"
        }
        if (change == "d_email") {
            tvConfirm!!.text = "Xác nhận xóa tài khoản"
        }
        val user = Firebase.auth.currentUser
        textInputLayout!!.editText!!.setText(user!!.email.toString())
        confirm!!.setOnClickListener {
            reAuthenticateAUser(password!!.text.toString(), choose)
        }
    }

    private fun initReAuthenticateUI() {
        confirm = findViewById(R.id.confirm_button)
        tvConfirm = findViewById(R.id.confirm_textview)
        textInputLayout = findViewById(R.id.textInputLayout_email)
        email = findViewById(R.id.confirm_email_edittext)
        password = findViewById(R.id.confirm_password_edittext)
    }

    private fun reAuthenticateAUser(pass: String, choose: Int) {
        val user = Firebase.auth.currentUser!!
        val credential = EmailAuthProvider.getCredential(user.email!!.toString(), pass)
        user.reauthenticate(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Xác nhận thành công", Toast.LENGTH_SHORT).show()
                    if (choose == 0) {
                        startActivity(Intent(this, ChangeEmailActivity::class.java))
                    }
                    if (choose == 1) {
                        startActivity(Intent(this, ChangePasswordActivity::class.java))
                    }
                    if (choose == 2) {
                        startActivity(Intent(this, DeleteAccountActivity::class.java))
                    }
                    else {
                        print("Không nhận được dữ liệu từ intent")
                    }
                } else {
                    password!!.error = "Sai mật khẩu!"
                }
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ReAuthenticateActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}