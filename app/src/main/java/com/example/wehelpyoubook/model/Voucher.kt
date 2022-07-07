package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

class Voucher(
    val userId : String,
    val description : String
) : DataCenter{
    @Override
    override fun getData() : Voucher {
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