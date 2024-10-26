package com.example.orderfood.screens.ListFood.DetailFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentDetailFoodPageBinding
import com.example.orderfood.helper.ManagmentCart
import com.example.orderfood.models.FoodModel

class DetailFoodPage : Fragment(R.layout.fragment_detail_food_page) {
    private var detailFoodPageBinding: FragmentDetailFoodPageBinding? = null;
    private val binding get()= detailFoodPageBinding!!;
    private val args: DetailFoodPageArgs by navArgs();
    lateinit var foodModel:  FoodModel;
    private var num = 1;
    private lateinit var managmentCart: ManagmentCart;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailFoodPageBinding = FragmentDetailFoodPageBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getArgs();
        setVariable()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setVariable() {
        managmentCart = ManagmentCart(requireActivity())
        binding.btnBack.setOnClickListener{ it.findNavController().popBackStack()}
        Glide.with(requireActivity()).load(foodModel.ImagePath).into(binding.imgFood)
        binding.tvPrice.text = "$ "+foodModel.Price
        binding.tvTitle.text = foodModel.Title
        binding.tvRating.text = foodModel.Star.toString() +" Rating"
        binding.ratingBar.rating = (foodModel.Star ?: 0).toFloat()
        binding.tvTotal.text = (num * foodModel.Price!!).toString()+ " $"
        binding.tvDetail.text = foodModel.Description
        binding.tvNum.text = num.toString()
        binding.btnMinus.setOnClickListener{
            if(num<1) return@setOnClickListener;
            num=num-1
            binding.tvNum.text = num.toString()
            binding.tvTotal.text = (num * foodModel.Price!!).toString()+ " $"
        }
        binding.btnAdd.setOnClickListener{

            num=num+1
            binding.tvNum.text = num.toString()
            binding.tvTotal.text = (num * foodModel.Price!!).toString()+ " $"

        }
        binding.btnAddToCart.setOnClickListener {
            foodModel.numberInCart = num;
            managmentCart.insertFood(foodModel)
        }



    }


    private fun getArgs(){
        foodModel = args.foodModel;
    }

    override fun onDestroy() {
        detailFoodPageBinding = null;
        super.onDestroy()

    }
}