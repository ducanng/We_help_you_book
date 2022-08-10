package com.example.wehelpyoubook

data class Review(var useId: String? = null,
                  var resId: String? = null,
                  var description: String? = null)


data class Orders(
    val urlImage : String? = null,
    var name: String? = null,
    var timeBooking: String? = null,
    var timeEnd: String? = null,
    var order: String? = null ) {
}