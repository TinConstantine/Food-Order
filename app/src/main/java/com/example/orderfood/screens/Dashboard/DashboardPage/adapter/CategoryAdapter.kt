package com.example.orderfood.screens.Dashboard.DashboardPage.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderfood.R
import com.example.orderfood.databinding.ViewholderCategoryBinding
import com.example.orderfood.models.CategoryModel
import com.example.orderfood.screens.Dashboard.DashboardPage.DashboardPageDirections

class CategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val itemBinding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderCategoryBinding.inflate(
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
        val categoryModel: CategoryModel = different.currentList[position];
        holder.itemBinding.tvCatName.text = categoryModel.Name
        when (position) {
            0 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_1_background)
            1 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_2_background)
            2 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_3_background)
            3 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_4_background)
            4 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_5_background)
            5 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_6_background)
            6 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_7_background)
            7 -> holder.itemBinding.imgCat.setBackgroundResource(R.drawable.cat_8_background)
        }
        val imgPath:Int = context.resources.getIdentifier(different.currentList[position].ImagePath, "drawable", holder.itemView.context.packageName);
        Glide.with(context).load(imgPath).into(holder.itemBinding.imgCat)
        holder.itemView.setOnClickListener{
            view ->
            val direction = DashboardPageDirections.actionDashboardPageToListFoodPage((categoryModel.Id)?:1,categoryModel.Name,null)
            view.findNavController().navigate(direction)
        }

    }

    private val differentCallback = object : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.Id == newItem.Id
                    && oldItem.ImagePath == newItem.ImagePath
                    && oldItem.Name == newItem.Name
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem;
        }
    }
    val different = AsyncListDiffer(this, differentCallback);
}