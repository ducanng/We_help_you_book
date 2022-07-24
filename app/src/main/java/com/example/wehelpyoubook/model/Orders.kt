package com.example.wehelpyoubook.model


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Orders(
    val userId : String? = null,
    val urlImage : String? = null,
    var name: String? = null,
    var timeBooking: String? = null,
    var timeEnd: String? = null,
    var order: String? = null ) {
}
