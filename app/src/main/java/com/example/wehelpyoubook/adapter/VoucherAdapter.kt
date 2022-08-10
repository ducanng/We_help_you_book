package com.example.wehelpyoubook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Voucher

class VoucherAdapter (
    private val context: Context,
    private val dataset: List<Voucher>,
<<<<<<< HEAD
    private val listener: (Voucher,String) -> Unit
) : RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>(){
    class VoucherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.voucher_image)
        val descTextView: TextView = view.findViewById((R.id.voucher_desc_tv))
        val buttonEdit : Button = view.findViewById(R.id.voucher_edit_bt)
        val buttonRemove : Button = view.findViewById(R.id.voucher_remove_bt)
=======
    private val listener: (Voucher) -> Unit
) : RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>(){
    class VoucherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.voucher_image)
        val percentTextView: TextView = view.findViewById(R.id.voucher_percent_tv)
        val descTextView: TextView = view.findViewById((R.id.voucher_desc_tv))
        val button : Button = view.findViewById(R.id.voucher_choose_bt)
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.voucher_linear_list,parent,false)
        return VoucherViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val voucher = dataset[position]
        context.resources
<<<<<<< HEAD
        holder.buttonEdit.setOnClickListener{
            listener(voucher,"Edit")
        }
        holder.buttonRemove.setOnClickListener{
            listener(voucher,"Remove")
        }
        voucher.imageUrl?.let { holder.imageView.setImageResource(it) }
=======
        holder.button.setOnClickListener{
            listener(voucher)
        }
        voucher.imageUrl?.let { holder.imageView.setImageResource(it) }
        holder.percentTextView.text = voucher.percentage.toString()
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
        holder.descTextView.text = voucher.description
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}