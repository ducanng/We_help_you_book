package com.example.wehelpyoubook.account

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.db.Database
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class PhoneNumberActivity : AppCompatActivity() {
    private var editPhone: EditText? = null
    private var btnPhone: Button? = null
    private var connect: Connection? = null
    private var connectionResult: String? = ""
    //var phoneNumber: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        editPhone = findViewById(R.id.edit_phone_number)
        btnPhone = findViewById(R.id.continue_button)
        var phone: String
        this@PhoneNumberActivity.btnPhone!!.setOnClickListener {
            phone = this@PhoneNumberActivity.editPhone!!.text.toString()
            //val msg = Toast.makeText(this@PhoneNumberActivity, str, Toast.LENGTH_LONG)
            //msg.show()
            try {
                val db = Database()
                connect = db.connectDB() // Connect to database
                if (connect == null) {
                    connectionResult = "Error in SQL Connection!"
                } else {
                    connectionResult = " successful"
                    val existPhone:Boolean = db.isCheckExistPhoneNumber(phone, connect!!)
                    try {
                        val query = "INSERT INTO account(so_dien_thoai) VALUES ('$phone')"
                        val stmt: Statement = connect!!.createStatement()
                        stmt.executeUpdate(query)
                    } catch (se: SQLException) {
                        Log.e("Error from SQLException", se.message!!)
                    }
                    connect!!.close()
                }
            } catch (ex: Exception) {
                connectionResult = ex.message
                Log.e("Error from exception", ex.message!!)
            }
        }
    }
}