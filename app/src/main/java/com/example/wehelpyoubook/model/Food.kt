package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

class Food (
    val resId : String,
    val name : String,
    val price : Int,
    val urlImage : String) : DataCenter{
    @Override
    override fun getData() : Food {
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