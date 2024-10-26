package com.example.orderfood.screens.Cart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.orderfood.databinding.ViewholderCartFoodBinding
import com.example.orderfood.helper.ChangeNumberItemsListener
import com.example.orderfood.helper.ManagmentCart
import com.example.orderfood.models.FoodModel
import java.util.ArrayList


class CartFoodAdapter(
    private val context: Context,
    private var list: ArrayList<FoodModel>,
    private val changeNumberItemsListener: ChangeNumberItemsListener,
) : RecyclerView.Adapter<CartFoodAdapter.CartFoodViewHolder>() {
    class CartFoodViewHolder(val binding: ViewholderCartFoodBinding) :
        RecyclerView.ViewHolder(binding.root)
    private var managmentCart: ManagmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartFoodViewHolder {
        return CartFoodViewHolder(
            ViewholderCartFoodBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        );
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun isListEmpty():Boolean{
        return list.isEmpty()
    }

    override fun onBindViewHolder(holder: CartFoodViewHolder, position: Int) {
        val foodModel = list[position]
        val total = (foodModel.numberInCart ?: 0) * (foodModel.Price ?: 0.0);
        holder.binding.tvTitle.text = foodModel.Title
        holder.binding.tvPrice.text = "$ ${foodModel.Price}"
        holder.binding.tvTotal.text = "$ ${total}"
        holder.binding.tvQuantity.text = foodModel.numberInCart.toString()
        Glide.with(holder.itemView.context).load(foodModel.ImagePath)
            .transform(CenterCrop(), RoundedCorners(30)).into(holder.binding.imgFood)
        holder.binding.btnAdd.setOnClickListener{
                managmentCart.plusNumberItem(list , position){
                    changeNumberItemsListener.change()
                    notifyDataSetChanged()
                }
        }
        holder.binding.btnMinus.setOnClickListener{
            managmentCart.minusNumberItem(list, position){
                changeNumberItemsListener.change()
                notifyDataSetChanged()

            }
        }

    }
//
//    private val differentCallback = object : DiffUtil.ItemCallback<FoodModel>() {
//        override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
//            return oldItem.Id == newItem.Id
//                    && oldItem.BestFood == newItem.BestFood
//                    && oldItem.Star == newItem.Star
//                    && oldItem.TimeId == newItem.TimeId
//                    && oldItem.CategoryId == newItem.CategoryId
//                    && oldItem.ImagePath == newItem.ImagePath
//                    && oldItem.LocationId == newItem.LocationId
//                    && oldItem.Price == newItem.Price
//                    && oldItem.Description == newItem.Description
//                    && oldItem.TimeValue == newItem.TimeValue
//                    && oldItem.Title == newItem.Title
//                    && oldItem.PriceId == newItem.PriceId
//
//        }
//
//        override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
//            return oldItem == newItem;
//        }
//    }
//    val different = AsyncListDiffer(this, differentCallback);
}