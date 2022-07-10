package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

data class User (
    private val Id : String,
    val avatarUrl: String,
    val name : String,
    val role : String,
    val email : String,
    val account : String,
    val password : String,
    val dayOfBirth : String,
//    val listBookedRestaurant : MutableSet<Restaurant> = mutableSetOf<Restaurant>(),
//    val listVoucher: MutableSet<Voucher> = mutableSetOf<Voucher>()
)
//    : DataCenter
//{
//    @Override
//    override fun getData() : User {
//        return this
//    }
//    override fun insertData() : Boolean{
//        return true
//    }
//    override fun deleteData() : Boolean{
//        return true
//    }
//    override fun updateData() : Boolean{
//        return true
//    }
//}