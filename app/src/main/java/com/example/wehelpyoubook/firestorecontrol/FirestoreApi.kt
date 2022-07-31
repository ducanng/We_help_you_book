package com.example.wehelpyoubook.firestorecontrol

import android.content.Context
import androidx.navigation.NavDestination
import com.example.wehelpyoubook.model.*
import com.google.firebase.firestore.ktx.toObjects

class FirestoreApi {
    fun getFoods(context: Context): List<Food>
    {
        var resList = mutableListOf<Food>()
        val doc = com.example.wehelpyoubook.scrapingdata.db.collection("Foods")
        doc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects<Food>().toMutableList()
        }
        return resList
    }
    fun getVouchers(context: Context): List<Voucher>
    {
        var resList = mutableListOf<Voucher>()
        val doc = com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers")
        doc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects<Voucher>().toMutableList()
        }
        return resList
    }
    fun getOrders(context: Context): List<Orders>
    {
        var resList = mutableListOf<Orders>()
        val doc = com.example.wehelpyoubook.scrapingdata.db.collection("MyOrders")
        doc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects<Orders>().toMutableList()
        }
        return resList
    }
    fun getRestaurant(context: Context,id : String): List<Restaurant>
    {
        var resList = mutableListOf<Restaurant>()
        val doc = com.example.wehelpyoubook.scrapingdata.db.collection("Restaurants")
        doc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects<Restaurant>().toMutableList()
        }
        return resList
    }
    fun uploadData(){

    }
}