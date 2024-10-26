package com.example.orderfood.screens.Dashboard.DashboardPage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orderfood.models.CategoryModel
import com.example.orderfood.models.FoodModel
import com.example.orderfood.models.LocationModel
import com.example.orderfood.models.PriceModel
import com.example.orderfood.models.TimeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardViewmodel(private val database: FirebaseDatabase) : ViewModel(){
    val listLocationModel =  MutableLiveData<List<LocationModel>>();
    val listTime = MutableLiveData<List<TimeModel>>();
    val listPrice = MutableLiveData<List<PriceModel>>();
    val listFood = MutableLiveData<List<FoodModel>>()
    val listCategory = MutableLiveData<List<CategoryModel>>();
    fun getLocation(){
        val ref = database.getReference("Location");
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               val list = mutableListOf<LocationModel>()
                if(snapshot.exists()){
                    snapshot.children.forEach{i -> list.add(i.getValue(LocationModel::class.java)!!)}
                    listLocationModel.value = list;
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }

    fun getTime(){
        val ref = database.getReference("Time");
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<TimeModel>()
                if(snapshot.exists()){
                    snapshot.children.forEach{i -> list.add(i.getValue(TimeModel::class.java)!!)}
                    listTime.value = list;
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }
    fun getPrice(){
        val ref = database.getReference("Price");
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<PriceModel>()
                if(snapshot.exists()){
                    snapshot.children.forEach{i -> list.add(i.getValue(PriceModel::class.java)!!)}
                    listPrice.value = list;
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }

    fun getBestFood(){
        val ref = database.getReference("Foods");
        val query = ref.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FoodModel>()
                if(snapshot.exists()){
                    snapshot.children.forEach{i -> list.add(i.getValue(FoodModel::class.java)!!)}
                    listFood.value = list;
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }

    fun getCategory(){
        val ref = database.getReference("Category");
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                if(snapshot.exists()){
                    snapshot.children.forEach{i -> list.add(i.getValue(CategoryModel::class.java)!!)}
                    listCategory.value = list;
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }


}