package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

class Review(
    val resId : String,
    val useId : String,
    val description : String
) : DataCenter {
    @Override
    override fun getData() : Review {
        return this
    }
    override fun insertData() : Boolean{
        return true
    }
    override fun deleteData() : Boolean{
        return true
    }
    override fun updateData() : Boolean{
        return true
    }
}