package com.example.wehelpyoubook.vouchercontroller
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.restaurentInterface.RestaurantEdit

class VoucherFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startActivity(Intent(activity, RestaurantEdit::class.java))
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}