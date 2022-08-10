package com.example.wehelpyoubook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Restaurant

class NearRestaurantAdapter (
    private val context: Context,
    private val dataset: List<Restaurant>,
    private val listener: (Restaurant) -> Unit
    ) : RecyclerView.Adapter<NearRestaurantAdapter.NearRestaurantViewHolder>(){
    class NearRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurant_image)
        val nameTextView: TextView = view.findViewById(R.id.restaurant_name)
        val rateTextView: TextView = view.findViewById((R.id.restaurant_rate))
<<<<<<< HEAD
        val addressTv : TextView = view.findViewById(R.id.restaurant_address_tx)
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearRestaurantViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.near_restaurant_gird_list,parent,false)
        return NearRestaurantViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NearRestaurantViewHolder, position: Int) {
        val res = dataset[position]

        val resources = context.resources
        Glide.with(context).load(res.imageUrl).into(holder.imageView)
        holder.imageView.setOnClickListener(){
            listener(res)
        }
        holder.nameTextView.text = res.name
<<<<<<< HEAD
        holder.addressTv.text = res.address
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
        holder.rateTextView.text = resources?.getString(R.string.rate,res.rate)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}