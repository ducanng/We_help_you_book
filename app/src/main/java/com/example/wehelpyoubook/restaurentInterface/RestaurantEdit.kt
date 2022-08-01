//package com.example.wehelpyoubook.restaurentInterface
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.core.widget.addTextChangedListener
//import com.example.wehelpyoubook.R
//import com.example.wehelpyoubook.databinding.ActivityRestaurantEditBinding
//import com.example.wehelpyoubook.model.Restaurant
//import com.example.wehelpyoubook.model.User
//import com.example.wehelpyoubook.model.Voucher
//import com.example.wehelpyoubook.vouchercontroller.VoucherListActivity
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.firestore.ktx.toObjects
//import com.google.firebase.ktx.Firebase
//
//class RestaurantEdit : AppCompatActivity() {
//    private lateinit var binding : ActivityRestaurantEditBinding
//    private var resId : String = ""
//    private var res : Restaurant = Restaurant()
//    private var resName : String = ""
//    private var restAddress : String = ""
//    private var resImage : String = ""
//    private var resRate : String = ""
//    private  var manager : User = User()
//    private var documentID : String = ""
//    private var actionType : String = "edit"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRestaurantEditBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        getRestaurantData()
//
//        binding.restaurantEditName.addTextChangedListener{
//        }
//        binding.restaurantEditAddress.addTextChangedListener{
//        }
//        binding.restaurantEditImage.addTextChangedListener{
//        }
//        binding.restaurantEditRate.addTextChangedListener{
//        }
//        binding.restaurantEditChangeButton.setOnClickListener{
//
//            voucherInputHandle()
//
//            when(actionType) {
//                "edit" -> {
//                    com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers").document(documentID).delete()
//                    com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers").add(
//                        Voucher(
//                            binding.voucherEditDescription.text.toString(),
//                            binding.voucherEditImage.text.toString().toInt(),
//                            binding.voucherEditSale.text.toString().toInt(),
//                            "",
//                            resId
//                        )
//                    )
//                    Toast.makeText(
//                        this@VoucherEdit,
//                        "Cập nhật thành công !!!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                "add" -> {
//                    com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers").add(
//                        Voucher(
//                            binding.voucherEditDescription.text.toString(),
//                            binding.voucherEditImage.text.toString().toInt(),
//                            binding.voucherEditSale.text.toString().toInt(),
//                            "",
//                            resId
//                        )
//                    )
//                    Toast.makeText(
//                        this@VoucherEdit,
//                        "Thêm thành công !!!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            startActivity(Intent(this@RestaurantEdit, VoucherListActivity::class.java))
//        }
//        binding.restaurantEditCancelButton.setOnClickListener(){
//            startActivity(Intent(this@RestaurantEdit, VoucherListActivity::class.java))
//        }
//    }
//    private fun getRestaurantData(){
//        db.collection("Restaurants")
//            .whereEqualTo("resID",manager.restaurantManager)
//            .get()
//            .addOnSuccessListener {
//                documentSnapshot ->
//                if (documentSnapshot.toObjects<Restaurant>().isNotEmpty()){
//                     = documentSnapshot.toObjects<User>()[0]
//                }
//            }
//    }
//    private fun getMangedRestaurantId() {
//        val auth = Firebase.auth.currentUser
//        var userId = auth!!.uid
//
//        val resDoc = db.collection("Users").whereEqualTo("id",userId)
//        resDoc.get().addOnSuccessListener { documentSnapshot ->
//            if (documentSnapshot.toObjects<User>().isNotEmpty()){
//                manager = documentSnapshot.toObjects<User>()[0]
//            }
//        }
//    }
//    private fun voucherInputHandle(){
//        voucherPercentage = binding.voucherEditSale.text.toString()
//        if (voucherPercentage == ""){
//            voucherPercentage = "5"
//            binding.voucherEditSale.setText(voucherPercentage)
//            voucherPercentage
//        }
//        binding.voucherEditDescription.setText("Voucher giảm $voucherPercentage%")
//        if (binding.voucherEditImage.text.toString() == "") {
//            binding.voucherEditImage.setText(voucherImUrl)
//        }
//    }
//}