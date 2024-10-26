package com.example.orderfood.screens.ListFood.ListFoodPage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orderfood.models.FoodModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ListFoodViewmodel(private val database: FirebaseDatabase) : ViewModel() {
    val listFood = MutableLiveData<List<FoodModel>>();

    fun getListFoodByCategory(categoryId: Int, searchText: String?) {
        val ref = database.getReference("Foods")
        val list = mutableListOf<FoodModel>();
        var query: Query;
        if (categoryId == -1) {
            Log.d("Cold","" + searchText);
            query = ref.orderByChild("Title").startAt(searchText).endAt(searchText + "\uf8ff")
        } else {
            query = ref.orderByChild("CategoryId").equalTo(categoryId.toDouble())
        }
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Cold", snapshot.toString())
                if (snapshot.exists()) {
                    Log.d("Cold", snapshot.toString())
                    snapshot.children.forEach { e ->
                        list.add((e.getValue(FoodModel::class.java) ?: FoodModel()))
                        listFood.value = list
                        Log.d("Cold", "List" + list.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Realtime DB", error.toString())
            }

        })
    }
}