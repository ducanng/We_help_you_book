package com.example.wehelpyoubook.accountcontrol.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.mybooking.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.toObjects
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
        val name = nameEdit!!.text.toString()
        db.collection("Users")
            .whereEqualTo("id", user.uid)
            .get()
            .addOnSuccessListener { documentSnap ->
                if(documentSnap.documents.isNotEmpty()) {
                    val curUser = documentSnap.toObjects<User>()[0]

                    val documentRemoveId = documentSnap.documents[0].id
                    db.collection("Users").document(documentRemoveId).delete()
                    db.collection("Users").add(
                        User(
                            curUser.Id,
                            "https://images.foody.vn/default/s50/user-default-female.png",
                            name,
                            curUser.role,
                            curUser.email,
                            curUser.email,
                            curUser.password,
                            "",
                            curUser.role
                        )
                    )
                }
            }
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
                    Toast.makeText(this, "Cập nhật thất bại.\nVui lòng thử lại", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@ChangeNameActivity, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}