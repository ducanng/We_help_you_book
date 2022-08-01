package com.example.wehelpyoubook.mybooking



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Orders
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.model.User
import com.google.firebase.firestore.ktx.toObjects

class MyBookingAdapter(
    private val context: MyBookingFragment,
    private val orders: List<Orders>,
    private val listener: (Orders) -> Unit
) : RecyclerView.Adapter<MyBookingAdapter.MyBookingAdapterViewHolder>() {

    class MyBookingAdapterViewHolder
        (private val view: View) : RecyclerView.ViewHolder(view) {
        //        val userId : String? = null,
        val imageView: ImageView = view.findViewById(R.id.restaurant_image)
        val resNameView: TextView = view.findViewById(R.id.my_booking_list_name)
        val timeBookingView: TextView = view.findViewById(R.id.time_booking)
        val timeEndView: TextView = view.findViewById(R.id.time_end)
        val voucherView: TextView = view.findViewById(R.id.voucher)
        val orderView: TextView = view.findViewById(R.id.order)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyBookingAdapterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_my_booking, parent, false)


        return MyBookingAdapterViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: MyBookingAdapterViewHolder,
                                  position: Int) {
        val mybooking = orders[position]
        val resources = context?.resources
        holder.itemView.setOnClickListener { listener(mybooking) }

        holder.timeBookingView.text = resources?.getString(R.string.time_booking,mybooking.timeBooking)
        mybooking.timeEnd = mybooking.timeBooking?.let { calcDifTime(it) }
        holder.timeEndView.text =  mybooking.timeEnd

        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID",mybooking.resID)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val resData = documentSnapshot.toObjects<Restaurant>()
            if (resData.isNotEmpty()) {
                Glide.with(context).load(resData[0].imageUrl).into(holder.imageView)
                holder.resNameView.text =  resData[0].name

            }
        }
        holder.voucherView.text = mybooking.voucher

        val countOrder : Int
        countOrder = position + 1
        holder.orderView.text= resources?.getString(R.string.order,(mybooking.order + countOrder))

    }


    private fun calcDifTime(tmpStr: String): String {
        if(tmpStr == ""){
            val res = tmpStr
            return res
        }
        val currentString = tmpStr
        val separated = currentString.split(":").toMutableList()

        val content = "Time ending: "
        if (separated[0] == "23"){
            separated[0] = "-1"
        }
        val difHour = separated[0].toInt() + 1
        val difMin = separated[1]
        val difSec = separated[2]
        val res =content + (difHour).toString() + " giờ " + difMin + " phút " + difSec + " giây"

        return res
    }


    override fun getItemCount(): Int {
        return orders.size
    }
}


