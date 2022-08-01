package com.example.wehelpyoubook.restaurentInterface

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.databinding.ActivityRestaurantEditBinding
import com.example.wehelpyoubook.home.HomeFragment
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class RestaurantEdit : AppCompatActivity() {
    private lateinit var binding : ActivityRestaurantEditBinding
    private var res : Restaurant = Restaurant()
    private var resName : String = ""
    private var restAddress : String = ""
    private var resImage : String = ""
    private var resRate : String = ""
    private  var manager : User = User()
    private var documentID : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMangedRestaurantId()

        binding.restaurantEditName.addTextChangedListener{
        }
        binding.restaurantEditAddress.addTextChangedListener{
        }
        binding.restaurantEditImage.addTextChangedListener{
        }
        binding.restaurantEditChangeButton.setOnClickListener{

            restaurantInputHandle()

            com.example.wehelpyoubook.scrapingdata.db.collection("Restaurants").document(documentID).delete()
            com.example.wehelpyoubook.scrapingdata.db.collection("Restaurants").add(
                Restaurant(
                    manager.restaurantManager,
                    binding.restaurantEditName.text.toString(),
                    binding.restaurantEditRate.text.toString(),
                    binding.restaurantEditAddress.text.toString(),
                    binding.restaurantEditImage.text.toString(),
                )
            )
            Toast.makeText(
                this@RestaurantEdit,
                "Cập nhật thành công !!!",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this@RestaurantEdit, RestaurantEdit::class.java))
        }
        binding.restaurantEditCancelButton.setOnClickListener(){
            startActivity(Intent(this@RestaurantEdit, MainActivity::class.java))
        }
    }
    private fun getRestaurantData(){

        db.collection("Restaurants")
            .whereEqualTo("resID",manager.restaurantManager)
            .get()
            .addOnSuccessListener {
                documentSnapshot ->
                if (documentSnapshot.toObjects<Restaurant>().isNotEmpty()){
                    documentID = documentSnapshot.documents[0].id
                    res = documentSnapshot.toObjects<Restaurant>()[0]
                    binding.restaurantEditName.setText(res.name)
                    binding.restaurantEditRate.setText(res.rate)
                    binding.restaurantEditAddress.setText(res.address)
                    binding.restaurantEditImage.setText(res.imageUrl)
                }
            }
    }
    private fun getMangedRestaurantId() {
        val auth = Firebase.auth.currentUser
        var userId = auth!!.uid

        val resDoc = db.collection("Users").whereEqualTo("id",userId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.toObjects<User>().isNotEmpty()){
                manager = documentSnapshot.toObjects<User>()[0]
                getRestaurantData()
            }
        }
    }
    private fun restaurantInputHandle(){

        if (binding.restaurantEditName.text.toString() == "") {
            binding.restaurantEditName.setText(res.name)
        }
        if (binding.restaurantEditAddress.text.toString() == "") {
            binding.restaurantEditAddress.setText(res.address)
        }
        if (binding.restaurantEditImage.text.toString() == "") {
            binding.restaurantEditImage.setText(res.imageUrl)
        }
    }
}