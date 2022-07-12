package com.example.wehelpyoubook.accountcontrol.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.wehelpyoubook.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class ChangePasswordActivity : AppCompatActivity() {
    private var saveBtn: Button? = null
    private var editNewPass: TextInputEditText? = null
    private var editReNewPass: TextInputEditText? = null

    private fun initUIChangePassword() {
        editNewPass = findViewById(R.id.change_password_edittext)
        editReNewPass = findViewById(R.id.change_repassword_edittext)
        saveBtn = findViewById(R.id.save_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        initUIChangePassword()

        saveBtn!!.setOnClickListener {
            val pass = this.editNewPass!!.text.toString()
            val repass = this.editReNewPass!!.text.toString()
            if (isValidatePassword(pass, editNewPass!!) && isValidatePassword(repass, editReNewPass!!)) {
                if (pass != repass) {
                    editReNewPass!!.error = "Mật khẩu không khớp"
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    changePassword(pass)
                }
            }
        }
    }
    private val passwordPattern: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    private fun isValidatePassword(passwordInput: String, nofitication: TextInputEditText): Boolean {
        // if password field is empty
        // it will display error message "Field can not be empty"
        return if (passwordInput.isEmpty()) {
            nofitication.error = "Không thể để trống"
            false
        } else if (!passwordPattern.matcher(passwordInput).matches()) {
            nofitication.error = "Mật khẩu yếu"
            false
        } else {
            nofitication.error = null
            true
        }
    }
    private fun changePassword(newPass: String) {
        val user = Firebase.auth.currentUser
        user!!.updatePassword(newPass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Cập nhật mật khẩu thất bại." +
                            "\nVui lòng thử lại!", Toast.LENGTH_SHORT).show()
                }
            }

    }


}