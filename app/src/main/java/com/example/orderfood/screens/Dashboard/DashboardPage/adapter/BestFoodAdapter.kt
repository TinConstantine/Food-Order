package com.example.orderfood.screens.Dashboard.DashboardPage.adapter

import android.annotation.SuppressLint
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
import com.example.orderfood.databinding.ViewholderBestfoodBinding
import com.example.orderfood.models.FoodModel
import com.example.orderfood.screens.Dashboard.DashboardPage.DashboardPageDirections

class BestFoodAdapter(private val context: Context) : RecyclerView.Adapter<BestFoodAdapter.ViewHolder>() {
    class ViewHolder(val itemBinding: ViewholderBestfoodBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderBestfoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return different.currentList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodModel: FoodModel = different.currentList[position];
        holder.itemBinding.tvTitle.text = foodModel.Title
        holder.itemBinding.tvPrice.text = "$ ${foodModel.Price}"
        holder.itemBinding.tvTime.text = "${foodModel.TimeValue} min"
        holder.itemBinding.tvStar.text = ""+ foodModel.Star;
        Glide.with(context).load(foodModel.ImagePath).transform(CenterCrop(), RoundedCorners(30)).into(holder.itemBinding.imgFood)
        holder.itemView.setOnClickListener{
            val direction = DashboardPageDirections.actionDashboardPageToDetailFoodPage(foodModel);
            it.findNavController().navigate(direction)
        }
    }

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
}