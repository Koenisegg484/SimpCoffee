package com.practiceprojects.simpcoffee.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practiceprojects.simpcoffee.Model.ItemsModel
import com.practiceprojects.simpcoffee.databinding.ViewholderOffersBinding

class OffersAdapter(val items: MutableList<ItemsModel>) : RecyclerView.Adapter<OffersAdapter.Viewholder>() {

    lateinit var context : Context

    inner class  Viewholder(val binding: ViewholderOffersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        context = parent.context
        val binding = ViewholderOffersBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.textView.text = items[position].title
        holder.binding.price.text = "₹ " + items[position].price.toString()

        Glide.with(holder.itemView.context).load(items[position].picUrl[0]).into(holder.binding.imageView3)

        holder.itemView.setOnClickListener{

        }
    }


}