package com.practiceprojects.simpcoffee.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.practiceprojects.simpcoffee.Adapter.CategoryAdapter
import com.practiceprojects.simpcoffee.Adapter.OffersAdapter
import com.practiceprojects.simpcoffee.Adapter.PopularsAdapter
import com.practiceprojects.simpcoffee.Model.MainViewModel
import com.practiceprojects.simpcoffee.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initPopulars()
        initOffers()
        bottomMenu()
    }

    private fun initCategory(){
        binding.categoryprogressBar.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.categoryprogressBar.visibility = View.GONE
        })
        viewModel.loadCategories()
    }

    private fun initPopulars(){
        binding.poppularprogressbar.visibility = View.VISIBLE
        viewModel.populars.observe(this, Observer{
            binding.popularrecyclerview.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.popularrecyclerview.adapter = PopularsAdapter(it)
            binding.poppularprogressbar.visibility = View.GONE
        })
        viewModel.loadPopulars()
    }

    private fun initOffers(){
        binding.offersprogressbar.visibility = View.VISIBLE
        viewModel.offers.observe(this, Observer {
            binding.offersrecyclerview.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.offersrecyclerview.adapter = OffersAdapter(it)
            binding.offersprogressbar.visibility = View.GONE
        })
        viewModel.loadOffers()
    }

    private fun bottomMenu(){
        binding.cartButton.setOnClickListener{
            startActivity(
                Intent(
                    this,
                    CartActivity::class.java
                )
            )
        }
    }
}