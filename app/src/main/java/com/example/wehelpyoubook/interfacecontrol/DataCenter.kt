package com.example.wehelpyoubook.interfacecontrol

import com.example.wehelpyoubook.model.Food
import java.util.*

interface DataCenter {
    fun getData() : DataCenter
    fun insertData() : Boolean
    fun deleteData() : Boolean
    fun updateData() : Boolean
}