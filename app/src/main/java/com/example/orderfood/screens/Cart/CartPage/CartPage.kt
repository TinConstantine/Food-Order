package com.example.orderfood.screens.Cart.CartPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentCartPageBinding
import com.example.orderfood.helper.ManagmentCart
import com.example.orderfood.screens.Cart.Adapter.CartFoodAdapter

class CartPage : Fragment(R.layout.fragment_cart_page) {
    private var cartPageBinding: FragmentCartPageBinding? = null;
    private val binding get() = cartPageBinding!!;
    private lateinit var managmentCart: ManagmentCart;
    private lateinit var rvCartAdapter: CartFoodAdapter;
    private var tax: Double = 0.0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartPageBinding = FragmentCartPageBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managmentCart = ManagmentCart(requireActivity())
        setVariable()
        calculateCart()
        initRv()
    }

    private fun initRv() {
        if (managmentCart.listCart.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.scrollCart.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.scrollCart.visibility = View.VISIBLE
        }
        binding.rvCart.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvCart.adapter = rvCartAdapter
    }

    private fun calculateCart() {
        var percentTax = 0.02
        var delivery = 10.0;
        tax = Math.round(managmentCart.totalFee * percentTax * 100.0) / 100.0
        var total = Math.round((managmentCart.totalFee + tax + delivery) * 100) / 100;
        var itemTotal = Math.round(managmentCart.totalFee * 100) / 100;
        binding.tvTotalFee.text = "$ ${itemTotal}"
        binding.tvTotalTax.text = "$ ${tax}"
        binding.tvDelivery.text = "$ ${delivery}"
        binding.tvTotal.text = "$ ${total}"

    }

    private fun setVariable() {
        rvCartAdapter = CartFoodAdapter(requireActivity(), managmentCart.listCart) {
            calculateCart()
            if (rvCartAdapter.isListEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.scrollCart.visibility = View.GONE
            }

        }

        binding.btnBackCart.setOnClickListener {
            Log.d("Cold", "Back in cart")
            it.findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        cartPageBinding = null;
        super.onDestroy()
    }

}