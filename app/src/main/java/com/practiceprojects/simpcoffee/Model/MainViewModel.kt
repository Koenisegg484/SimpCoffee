package com.practiceprojects.simpcoffee.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance("https://simpcoffee-e3bbc-default-rtdb.asia-southeast1.firebasedatabase.app/")

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _populars = MutableLiveData<MutableList<ItemsModel>>()
    private val _offers = MutableLiveData<MutableList<ItemsModel>>()

    val category: LiveData<MutableList<CategoryModel>> = _category
    val populars: LiveData<MutableList<ItemsModel>> = _populars
    val offers: LiveData<MutableList<ItemsModel>> = _offers

    fun loadCategories() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val category = childSnapshot.getValue(CategoryModel::class.java)
                    category?.let { categoryList.add(it) }
                }
                _category.value = categoryList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Failed to load categories: ${error.message}")
            }
        })
    }

    fun loadPopulars(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ItemsModel>()
                for(child in snapshot.children){
                    val item = child.getValue(ItemsModel::class.java)
                    item?.let { itemList.add(it) }
                }
                _populars.value = itemList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Failed to load Items: ${error.message}")
            }

        })
    }

    fun loadOffers(){
        val ref = firebaseDatabase.getReference("Offers")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ItemsModel>()
                for(child in snapshot.children){
                    val item = child.getValue(ItemsModel::class.java)
                    item?.let { itemList.add(it) }
                }
                _offers.value = itemList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Failed to load Offers: ${error.message}")
            }

        })
    }
}