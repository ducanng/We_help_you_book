package com.example.wehelpyoubook.accountcontrol

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.user.EmailActivity


open class HomeSignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_sign_in)
        //Email SignIn
        findViewById<View>(R.id.email_signin_button).setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
//        findViewById<View>(R.id.google).setOnClickListener {
//        }
    }
}
