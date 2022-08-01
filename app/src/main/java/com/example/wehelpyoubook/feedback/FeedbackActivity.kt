package com.example.wehelpyoubook.feedback

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.home.HomeFragment
import com.example.wehelpyoubook.mybooking.MyBookingFragment

class Feedback : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.title = "Feedback"
        setContentView(R.layout.fragment_feedback)
        val edit1 = findViewById<View>(R.id.edit1) as EditText
        val edit2 = findViewById<View>(R.id.edit2) as EditText
        val btn = findViewById<View>(R.id.button) as Button
        btn.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/html"
            i.putExtra(Intent.EXTRA_EMAIL, "datnguyen180502@gmail.com")
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("datnguyen180502@gmail.com"))
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback From App")
            i.putExtra(
                Intent.EXTRA_TEXT, """Name:${edit1.text}
 Message:${edit2.text}"""
            )
            try {
                startActivity(Intent.createChooser(i, "Please select Email"))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@Feedback,
                    "There are no Email Clients",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}