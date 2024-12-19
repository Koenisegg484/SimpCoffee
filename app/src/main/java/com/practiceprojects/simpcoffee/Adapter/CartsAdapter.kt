package com.practiceprojects.simpcoffee.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagementCart
import com.google.firebase.database.core.view.Change
import com.practiceprojects.simpcoffee.Helper.ChangeNumberItemsListener
import com.practiceprojects.simpcoffee.Model.ItemsModel
import com.practiceprojects.simpcoffee.databinding.ViewholderCartBinding

class CartsAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartsAdapter.Viewholder>() {

    inner class Viewholder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val managementCart = ManagementCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartsAdapter.Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartsAdapter.Viewholder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.carttitletext.text = item.title
        holder.binding.feeeachitem.text = "Rs. ${item.price}"
        holder.binding.toaleachitem.text = "Rs. ${Math.round(item.numberInCart*item.price)}"
        holder.binding.cartquantity.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0]).apply(RequestOptions().transform(CenterCrop())).into(holder.binding.picCart)

        holder.binding.cartadd.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

        holder.binding.cartminus.setOnClickListener {
            managementCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }
}