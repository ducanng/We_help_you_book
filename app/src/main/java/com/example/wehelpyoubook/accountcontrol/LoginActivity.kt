package com.example.wehelpyoubook.accountcontrol

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.HomeActivity
import com.example.wehelpyoubook.R

class LoginActivity : AppCompatActivity() {
    private var editPass: EditText? = null
    private var btnCallLogin: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editPass = findViewById(R.id.login_password_edittext)
        //val pass = this@LoginActivity.editPass!!.text.toString()
        btnCallLogin = findViewById<View>(R.id.phone_number_button) as Button
        btnCallLogin!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}