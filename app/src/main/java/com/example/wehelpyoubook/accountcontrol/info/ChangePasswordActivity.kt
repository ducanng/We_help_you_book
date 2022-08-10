package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import com.example.wehelpyoubook.MainActivity
=======
<<<<<<< HEAD
=======
import com.example.wehelpyoubook.MainActivity
>>>>>>> main
>>>>>>> an
import com.example.wehelpyoubook.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
<<<<<<< HEAD
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
=======
<<<<<<< HEAD
=======
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
>>>>>>> main
>>>>>>> an
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
    private fun isValidatePassword(passwordInput: String, nofitication: TextInputEditText): Boolean {
        // if password field is empty
        // it will display error message "Field can not be empty"
        return if (passwordInput.isEmpty()) {
            nofitication.error = "Không thể để trống"
            false
        } else if (passwordInput.length < 6) {
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
                    startActivity(Intent(this, UserInformationActivity::class.java))
                    Toast.makeText(this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Cập nhật mật khẩu thất bại." +
                            "\nVui lòng thử lại!", Toast.LENGTH_SHORT).show()
                }
            }

    }
<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
>>>>>>> an
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangePasswordActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
<<<<<<< HEAD
=======
>>>>>>> main
>>>>>>> an

}