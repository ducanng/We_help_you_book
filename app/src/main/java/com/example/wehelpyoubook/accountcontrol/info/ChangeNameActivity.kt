package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ChangeNameActivity : AppCompatActivity() {
    private var nameEdit: EditText? = null
    private var save: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_change_name)
        nameEdit = findViewById(R.id.name_edittext)
        save = findViewById(R.id.save_button)
        save!!.setOnClickListener {
            saveDisplayName()
        }
    }
    private fun saveDisplayName() {
        val user = Firebase.auth.currentUser ?: return
        val profileUpdates = userProfileChangeRequest {
            displayName = nameEdit!!.text.toString()
            println("Tên " + nameEdit!!.text.toString())
            println(displayName)
        }
        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Cập nhật thất bại.\nVui lòng thử lại", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeNameActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}
