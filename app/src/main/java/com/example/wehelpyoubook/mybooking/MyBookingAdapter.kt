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

class ContentItem(val urlImage : String? = null,
                  var name: String? = null,
                  var timeBooking: String? = null,
                  var timeEnd: String? = null,
                  var order: String? = null)

    class MyBookingAdapter(
        private val context: MyBookingFragment,
        private val orders: List<Orders>,
        private val listener: (Orders) -> Unit
) : RecyclerView.Adapter<MyBookingAdapter.MyBookingAdapterViewHolder>() {



    class MyBookingAdapterViewHolder
        (private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurant_image)
        val nameView: TextView = view.findViewById(R.id.my_booking_list_name)
        val timeBookingView: TextView = view.findViewById(R.id.time_booking)
        val timeEndView: TextView = view.findViewById(R.id.time_end)
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

        holder.nameView.text =  mybooking.name
        holder.timeBookingView.text = mybooking.timeBooking
        mybooking.timeEnd = mybooking.timeBooking?.let { calcDifTime(it) }
        holder.timeEndView.text =  mybooking.timeEnd
        val countOrder : Int
        countOrder = position + 1
        holder.orderView.text =  (mybooking.order + countOrder)
        Glide.with(context).load(mybooking.urlImage).into(holder.imageView)

    }

    private fun calcDifTime(tmpStr: String): String {
        val currentString = tmpStr
        val separated = currentString.split(":").toMutableList()

        val content = "Time ending: "
        if (separated[1] == "23"){
            separated[1] = "-1"
        }
        val difHour = separated[1].toInt() + 1
        val difMin = separated[2]
        val difSec = separated[3]
        val res =content + (difHour).toString() + " giờ " + difMin + " phút " + difSec + " giây"

        return res
    }


    override fun getItemCount(): Int {
        return orders.size
    }
}


