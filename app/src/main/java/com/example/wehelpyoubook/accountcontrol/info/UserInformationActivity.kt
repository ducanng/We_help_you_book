package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.auth.ReAuthenticateActivity
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initUIUserInfo()
        val it = this.intent
        val change = it.getIntExtra("change", 0)
        if (change == 1) {
            setInfoUserAfterChange()
        } else {
            setInfoUser()
        }
        checkEmailVerification()
        changeNameImg!!.setOnClickListener {
            val intent = Intent(this, ChangeNameActivity::class.java)
            startActivity(intent)
        }
        changeEmailImg!!.setOnClickListener {
            val intent = Intent(this, ReAuthenticateActivity::class.java)
            intent.putExtra("choose", "c_email")
            intent.putExtra("pos", 0)
            startActivity(intent)
            finish()
        }
        changePassword!!.setOnClickListener {
            val intent = Intent(this, ReAuthenticateActivity::class.java)
            intent.putExtra("choose", "c_password")
            intent.putExtra("pos", 1)
            startActivity(intent)
            finish()
        }
        deleteAccount!!.setOnClickListener {
            val intent = Intent(this, ReAuthenticateActivity::class.java)
            intent.putExtra("choose", "d_account")
            intent.putExtra("pos", 2)
            startActivity(intent)
            finish()
        }
        logout!!.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setInfoUser() {
        val user = Firebase.auth.currentUser ?: return
        name!!.text = user.displayName
        println(name)
        println(user.displayName)
        email!!.text = user.email
    }
    private fun setInfoUserAfterChange() {
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
