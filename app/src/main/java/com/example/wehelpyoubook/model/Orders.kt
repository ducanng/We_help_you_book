package com.example.wehelpyoubook.model


data class Orders(
    val userId : String? = null,
    val resID: String? = null,
    var timeBooking: String? = null,
    var timeEnd: String? = null,
    var order: String? = null,
    var voucher: String? = null
)
