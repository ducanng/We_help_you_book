package com.example.wehelpyoubook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Restaurant

class NearRestaurantAdapter (
    private val context: Context,
    private val dataset: List<Restaurant>
) : RecyclerView.Adapter<NearRestaurantAdapter.NearRestaurantViewHolder>(){
    class NearRestaurantViewHolder(private  val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurant_image)
        val nameTextView: TextView = view.findViewById(R.id.restaurant_name)
        val rateTextView: TextView = view.findViewById((R.id.restaurant_rate))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearRestaurantViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.near_restaurant_gird_list,parent,false)
        return NearRestaurantViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NearRestaurantViewHolder, position: Int) {
        val res = dataset[position]
        holder.imageView.setImageResource(res.imageResourceId)
        val resources = context?.resources
        holder.nameTextView.text = res.name
        holder.rateTextView.text = resources?.getString(R.string.rate,res.rate)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}