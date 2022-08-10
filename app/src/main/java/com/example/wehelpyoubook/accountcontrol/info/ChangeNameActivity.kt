package com.example.wehelpyoubook.accountcontrol.info

<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Intent
=======
<<<<<<< HEAD
=======
import android.content.Intent
>>>>>>> main
>>>>>>> an
=======
import android.content.Intent
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.wehelpyoubook.MainActivity
=======
<<<<<<< HEAD
=======
import com.example.wehelpyoubook.MainActivity
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import com.example.wehelpyoubook.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ChangeNameActivity : AppCompatActivity() {
    private var nameEdit: EditText? = null
    private var save: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
<<<<<<< HEAD
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
=======
<<<<<<< HEAD
=======
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
>>>>>>> main
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
                }
            }
    }
}
=======
>>>>>>> an
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
                    val intent = Intent(this, UserInformationActivity::class.java)
                    intent.putExtra("change", 1)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Cập nhật thất bại.\nVui lòng thử lại", Toast.LENGTH_SHORT).show()
                }
            }
    }
<<<<<<< HEAD
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeNameActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
>>>>>>> an
=======
}
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
