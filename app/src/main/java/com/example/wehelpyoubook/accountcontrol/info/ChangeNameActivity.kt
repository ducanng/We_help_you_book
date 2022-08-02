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
import java.util.regex.Matcher
import java.util.regex.Pattern

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
            val name = nameEdit!!.text.toString()
            if (isValidateName(name)) {
                saveDisplayName(name)
            }
            else {
                Toast.makeText(this, "Cập nhật thất bại.\nVui lòng thử lại", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isPatternName(name: String): Boolean {
        val pattern: Pattern
        val namePattern = "(?=.*[a-z])(?=.*[A-Z]).{4,}"
        pattern = Pattern.compile(namePattern)
        val matcher: Matcher = pattern.matcher(name)
        return matcher.matches()
    }
    private fun isCharacterSpecial(name: String): Boolean {
        val pattern: Pattern
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val namePattern = "(?=.*[$specialCharacters]).{4,}"
        pattern = Pattern.compile(namePattern)
        val matcher: Matcher = pattern.matcher(name)
        return matcher.matches()
    }
    private fun isValidateName(name: String): Boolean {
        if (name == "") {
            nameEdit!!.error = "Không thể để trống"
            return false
        }
        if (name.length < 4) {
            nameEdit!!.error = "Đặt đầy đủ họ tên"
            return false
        }
        if (!isPatternName(name) || isCharacterSpecial(name)) {
            nameEdit!!.error = "Tên chỉ chứa kí tự [A-Z] và [a-z]"
            return false
        }
        return true
    }

    private fun saveDisplayName(name : String) {
        val user = Firebase.auth.currentUser ?: return
        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }
        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Cập nhật thất bại.\nVui lòng kiểm tra internet", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeNameActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}
