package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.homescreen.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserInformationActivity : AppCompatActivity() {
    private var name: TextView? = null
    private var email: TextView? = null
    private var changeNameImg: ImageView? = null
    private var changeEmailImg: ImageView? = null
    private var changePassword: TextView? = null
    private var deleteAccount: TextView? = null
    private var checkVerifyImage: ImageView? = null
    private var verifyEmailText: TextView? = null
    private var verifyEmail: Button? = null
    private var logout: Button? = null

    private fun initUIUserInfo() {
        name = findViewById(R.id.name_edit_textview)
        changeNameImg = findViewById(R.id.edit_name_image)
        email = findViewById(R.id.email_edit_textview)
        changeEmailImg = findViewById(R.id.edit_email_image)
        checkVerifyImage = findViewById(R.id.check_email_verify_image)
        verifyEmailText = findViewById(R.id.verify_email_textview)
        verifyEmail = findViewById(R.id.verify_email_button)
        changePassword = findViewById(R.id.change_password)
        deleteAccount = findViewById(R.id.delete_account)
        logout = findViewById(R.id.logout_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)
        initUIUserInfo()
        setInfoUser()
        checkEmailVerification()
        changeNameImg!!.setOnClickListener {
            val intent = Intent(this, ChangeNameActivity::class.java)
            startActivity(intent)
        }
        changeEmailImg!!.setOnClickListener {
            //set
        }
        changePassword!!.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        deleteAccount!!.setOnClickListener {
            //set click
        }
        logout!!.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    fun setInfoUser() {
        val user = Firebase.auth.currentUser ?: return
        name!!.text = user.displayName
        println(name)
        println(user.displayName)
        email!!.text = user.email
    }

    private fun checkEmailVerification() {
        val user = Firebase.auth.currentUser ?: return
        if (user.isEmailVerified) {
            checkVerifyImage!!.setImageResource(R.drawable.icon_yes)
            verifyEmailText!!.visibility = View.GONE
            verifyEmail!!.visibility = View.GONE
        }
    }
}