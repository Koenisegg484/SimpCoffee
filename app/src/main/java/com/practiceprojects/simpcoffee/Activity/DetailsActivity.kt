package com.practiceprojects.simpcoffee.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagementCart
import com.practiceprojects.simpcoffee.Adapter.SizeAdapter

import com.practiceprojects.simpcoffee.Model.ItemsModel
import com.practiceprojects.simpcoffee.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity() {
    lateinit var binding: ActivityDetailsBinding
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        val sizelist = ArrayList<String>()
        sizelist.add("1")
        sizelist.add("2")
        sizelist.add("3")
        sizelist.add("4")

        binding.sizerecyclerview.adapter = SizeAdapter(sizelist)
        binding.sizerecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }
        Glide.with(this).load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100))).into(binding.imageView5)
    }

    @SuppressLint("SetTextI18n")
    private fun bundle() {
        binding.apply {
            item = intent.getParcelableExtra("object")
                ?: run {
                    Log.e("DetailsActivity", "Item is null. Finishing activity.")
                    Toast.makeText(this@DetailsActivity, "Item data is missing", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity if no valid data is provided
                    return
                }

            titletext.text = item.title
            descriptiontext.text = item.description
            pricetext.text = "Rs. " + item.price
            ratingBar2.rating = item.rating.toFloat()

            addtocart.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    quantity.text.toString()
                )
                managementCart.insertItems(item)
            }

            backbutton.setOnClickListener {
                startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
            }

            addcart.setOnClickListener {
                quantity.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }

            minuscart.setOnClickListener {
                if (item.numberInCart >= 2){
                    quantity.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }
    }

}