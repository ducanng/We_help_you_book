package com.example.wehelpyoubook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Food


class FoodAdapter(
    private val context: Context,
    private val foodList : ArrayList<Food>,
): RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_food, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val food: Food = foodList[position]
<<<<<<< HEAD
<<<<<<< HEAD
=======
        println(food.urlImage)
        println("lll")
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        Glide.with(context).load(food.urlImage).into(holder.foodImageView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImageView: ImageView = itemView.findViewById(R.id.food_imageView)
    }

}