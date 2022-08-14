package com.example.wehelpyoubook.userinfor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.accountcontrol.info.UserInformationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserInforFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val user = Firebase.auth.currentUser
        if (user != null) {
            startActivity(Intent(activity, UserInformationActivity::class.java))
        } else {
            startActivity(Intent(activity, HomeSignInActivity::class.java))
        }

        //startActivity(Intent(getActivity(), HomeFragment::class.java))


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}