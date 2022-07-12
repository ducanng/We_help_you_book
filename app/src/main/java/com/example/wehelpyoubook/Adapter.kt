package com.example.wehelpyoubook
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User

class Adapter(private val reviewList : ArrayList<Review>): RecyclerView.Adapter<Adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_review,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        val review : Review = reviewList[position]
        holder.useId.text = review.useId
        holder.resId.text = review.resId
        holder.description.text = review.description
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val resId: TextView = itemView.findViewById(R.id.resID)
        val useId: TextView = itemView.findViewById(R.id.useID)
        val description: TextView = itemView.findViewById(R.id.description)
    }
}