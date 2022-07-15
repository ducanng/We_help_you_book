package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

data class User (
    val Id : String? = null,
    val avatarUrl: String? = null,
    val name : String? = null,
    val role : String? = null,
    val email : String? = null,
    val account : String? = null,
    val password : String? = null,
    val dayOfBirth : String? = null,
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