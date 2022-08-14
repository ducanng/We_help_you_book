package com.example.wehelpyoubook.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.home.HomeFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LogoutFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Firebase.auth.signOut()
        val user = Firebase.auth.currentUser
        if (user != null){

            Toast.makeText(activity, "Failed Logout", Toast.LENGTH_SHORT).show()

            startActivity(Intent(activity, HomeFragment::class.java))
        }
        else {
            Toast.makeText(activity, "Logout Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, HomeSignInActivity::class.java))
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}


