package com.practiceprojects.simpcoffee.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practiceprojects.simpcoffee.Activity.DetailsActivity
import com.practiceprojects.simpcoffee.Model.ItemsModel
import com.practiceprojects.simpcoffee.databinding.ViewholderPopularBinding

class PopularsAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<PopularsAdapter.Viewholder>() {

        private var context : Context?= null


    inner class Viewholder(val binding: ViewholderPopularBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularsAdapter.Viewholder {
        context = parent.context
        val binding= ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)

        return Viewholder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PopularsAdapter.Viewholder, position: Int) {
        holder.binding.titletext.text = items[position].title
        holder.binding.detailstxt.text = items[position].description
        holder.binding.pricetxt.text = "₹"+items[position].price.toString()
        holder.binding.ratingBar.rating = items[position].rating.toFloat()

        Glide.with(holder.itemView.context).load(items[position].picUrl[0]).into(holder.binding.picture)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}