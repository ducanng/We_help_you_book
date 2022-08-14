package com.example.wehelpyoubook.restaurentInterface

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wehelpyoubook.R


class EditRestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startActivity(Intent(activity, RestaurantEdit::class.java))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}