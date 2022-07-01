package com.example.wehelpyoubook.data

import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.interfacecontrol.DataCenter
import com.example.wehelpyoubook.model.Food
import com.example.wehelpyoubook.model.Restaurant

class NearRestaurantData : DataCenter {
    private var listRestaurant = mutableSetOf<Restaurant>()
    fun loadNearRestaurant() : MutableSet<Restaurant> {
        listRestaurant = mutableSetOf(
            Restaurant(
                R.drawable.khoai,
                "Nhà Hàng Khoái - Món Ngon Nha Trang",
                "8.3"
            ),
            Restaurant(
                R.drawable.mandala,
                "Nhà Hàng Chay Mandala",
                "7.0"
            ),
            Restaurant(
                R.drawable.organic,
                "The Organic - Nhà Hàng Chay",
                "8.0"
            ),
            Restaurant(
                R.drawable.vajra,
                "Vajra - Nhà Hàng Chay",
                "7.1"
            ),
            Restaurant(
                R.drawable.wmc_group,
                "Hệ Thống WMC Group",
                "7.5"
            ),
            Restaurant(
                R.drawable.bistrot_de_saigon,
                "Nhà Hàng Bistrot De Saigon",
                "8.3"
            ),
        )
        return listRestaurant
    }
//    fun addRestaurant(restaurant: Restaurant) : Boolean{
//        return listRestaurant.add(restaurant)
//    }
//    fun removeRestaurant(restaurant: Restaurant) : Boolean{
//        return listRestaurant.remove(restaurant)
//    }
//    fun getRestaurant(restaurant: Restaurant) : Restaurant? {
//        return listRestaurant.find { (it == restaurant) }
//    }
    override fun getData() : NearRestaurantData {
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