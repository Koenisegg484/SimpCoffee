package com.practiceprojects.simpcoffee.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagementCart
import com.practiceprojects.simpcoffee.Adapter.CartsAdapter
import com.practiceprojects.simpcoffee.Helper.ChangeNumberItemsListener
import com.practiceprojects.simpcoffee.R
import com.practiceprojects.simpcoffee.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {

    lateinit var binding : ActivityCartBinding
    lateinit var managementCart: ManagementCart
    private var tax : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)
        calculateCart()
        setVariable()
        initCartList()

    }


    private fun initCartList(){
        with(binding){
            cartView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            cartView.adapter = CartsAdapter(managementCart.getListCart(), this@CartActivity, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }
            })
        }
    }
    private fun setVariable(){
        binding.backbutton2.setOnClickListener {
            finish()
        }
    }

    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100)/100.0
        val total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100)/100
        val itemTotal = Math.round(managementCart.getTotalFee() * 100)/100

        with(binding){
            totalfeetxt.text = "Rs. $itemTotal"
            taxtxt.text = "Rs. $tax"
            deliveryfeetxt.text = "Rs. $delivery"
            finalbilltext.text = "Rs. $total"
        }
    }
}