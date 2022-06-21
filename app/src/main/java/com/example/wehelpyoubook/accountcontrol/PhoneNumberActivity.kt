package com.example.wehelpyoubook.accountcontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.database.Database


class PhoneNumberActivity : AppCompatActivity() {
    private var editPhone: EditText? = null
    private var btnPhone: Button? = null
    //private var tag: String? = ""
    //var phoneNumber: Long = 0
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        editPhone = findViewById(R.id.phone_number_edittext)
        btnPhone = findViewById(R.id.continue_button)
        var phone: String
        this@PhoneNumberActivity.btnPhone!!.setOnClickListener {
            phone = this@PhoneNumberActivity.editPhone!!.text.toString()
            val existPhone:Boolean = Database().isCheckExist( "account", "so_dien_thoai", phone)
            if (existPhone) {
                val msg = Toast.makeText(this@PhoneNumberActivity, "Login", Toast.LENGTH_LONG)
                msg.show()
                val intent = Intent(this@PhoneNumberActivity, LoginActivity::class.java)
                startActivity(intent)
            } else  {
                val table = "account"
                val column:List<String> = listOf("so_dien_thoai")
                val values:List<String> = listOf(phone)
                if (Database().insert(table,column, values)) {
                    val msg = Toast.makeText(this@PhoneNumberActivity, "Register", Toast.LENGTH_LONG)
                    msg.show()
                    val intent = Intent(this@PhoneNumberActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}