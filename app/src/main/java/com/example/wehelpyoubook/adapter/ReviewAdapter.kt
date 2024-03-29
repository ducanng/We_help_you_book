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
import com.example.wehelpyoubook.model.Review
import com.example.wehelpyoubook.model.User
import com.google.firebase.firestore.ktx.toObjects


class ReviewAdapter(
    private val context: Context,
    private val reviewList : ArrayList<Review>
    ): RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_review, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review: Review = reviewList[position]
        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Users")
            .whereEqualTo("id",review.useId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val userData = documentSnapshot.toObjects<User>()
            if (userData.isNotEmpty()) {
                Glide.with(context).load(userData[0].avatarUrl).into(holder.imageView)
                holder.userName.text = userData[0].name
            }

        }
        holder.description.text = review.description
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.useID)
        val description: TextView = itemView.findViewById(R.id.description)
        val imageView : ImageView = itemView.findViewById(R.id.user_imageView)
    }
}
