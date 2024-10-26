package com.example.orderfood.screens.ListFood.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.orderfood.databinding.ViewholderFoodListfoodBinding
import com.example.orderfood.models.FoodModel
import com.example.orderfood.screens.ListFood.ListFoodPage.ListFoodPageDirections

class FoodListAdapter(private val context: Context) :
    RecyclerView.Adapter<FoodListAdapter.FoodListAdapterViewHolder>() {
    class FoodListAdapterViewHolder(val binding: ViewholderFoodListfoodBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differentCallback = object : DiffUtil.ItemCallback<FoodModel>() {
        override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
            return oldItem.Id == newItem.Id
                    && oldItem.BestFood == newItem.BestFood
                    && oldItem.Star == newItem.Star
                    && oldItem.TimeId == newItem.TimeId
                    && oldItem.CategoryId == newItem.CategoryId
                    && oldItem.ImagePath == newItem.ImagePath
                    && oldItem.LocationId == newItem.LocationId
                    && oldItem.Price == newItem.Price
                    && oldItem.Description == newItem.Description
                    && oldItem.TimeValue == newItem.TimeValue
                    && oldItem.Title == newItem.Title
                    && oldItem.PriceId == newItem.PriceId

        }

        override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
            return oldItem == newItem;
        }
    }
    val different = AsyncListDiffer(this, differentCallback);


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapterViewHolder {
        return FoodListAdapterViewHolder(
            ViewholderFoodListfoodBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return different.currentList.size
    }

    override fun onBindViewHolder(holder: FoodListAdapterViewHolder, position: Int) {
        val foodModel = different.currentList[position];
        holder.binding.tvTitle.text = foodModel.Title
        holder.binding.tvPrice.text = "$ ${foodModel.Price}"
        holder.binding.tvTime.text = "${foodModel.TimeValue} min"
        holder.binding.tvRate.text = ""+ foodModel.Star;
        Glide.with(context).load(foodModel.ImagePath).transform(CenterCrop(), RoundedCorners(30)).into(holder.binding.imgFoodList)
        holder.itemView.setOnClickListener{
            val direction = ListFoodPageDirections.actionListFoodPageToDetailFoodPage(foodModel);
            it.findNavController().navigate(direction)
        }
    }

}