package com.practiceprojects.simpcoffee.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practiceprojects.simpcoffee.Model.CategoryModel
import com.practiceprojects.simpcoffee.R
import com.practiceprojects.simpcoffee.databinding.ViewholderCategoryBinding


class CategoryAdapter (val items: MutableList<CategoryModel>) :
        RecyclerView.Adapter<CategoryAdapter.Viewholder>(){

            private  lateinit var context: Context
            private var selectedIndex = -1
            private var lastSelectedIndex = -1

    inner class Viewholder(val binding: ViewholderCategoryBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener{
            lastSelectedIndex = selectedIndex
            selectedIndex = position
            notifyItemChanged(lastSelectedIndex)
            notifyItemChanged(selectedIndex)
        }

        if (selectedIndex == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.button_bg)
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.edittext_bg)
        }
    }
}