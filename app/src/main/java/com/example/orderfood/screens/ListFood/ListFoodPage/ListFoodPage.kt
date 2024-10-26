package com.example.orderfood.screens.ListFood.ListFoodPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.orderfood.MainActivity
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentListFoodPageBinding
import com.example.orderfood.screens.ListFood.Adapter.FoodListAdapter
import kotlin.properties.Delegates

class ListFoodPage : Fragment(R.layout.fragment_list_food_page) {
    private var listFoodPageBinding: FragmentListFoodPageBinding? = null;
    private val binding get() = listFoodPageBinding!!;
    private var categoryId by Delegates.notNull<Int>();
    private var categoryName: String? = null;
    private var searchText: String? = null;
    private val args: ListFoodPageArgs by navArgs();
    private lateinit var listFoodViewmodel: ListFoodViewmodel;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFoodViewmodel = ListFoodViewmodel((activity as MainActivity).database);
        listFoodPageBinding = FragmentListFoodPageBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getArgs();
        getListFood();
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getListFood() {
        binding.progressBar.visibility = View.VISIBLE;
        listFoodViewmodel.getListFoodByCategory(categoryId, searchText);
        listFoodViewmodel.listFood.observe(viewLifecycleOwner){ list->
            if(list.isNotEmpty()){
                binding.rvFood.layoutManager = GridLayoutManager(context, 2)
                val adapter = FoodListAdapter(requireActivity());
                adapter.different.submitList(list);
                binding.rvFood.adapter = adapter;
                binding.progressBar.visibility = View.GONE;
            }
        }
    }

    private fun getArgs(){
        categoryId = args.categoryId
        categoryName = args.categoryName
        searchText = args.searchText;
        binding.tvTitle.text = categoryName;
        binding.btnBack.setOnClickListener{ view->
            view.findNavController().popBackStack()
        }

    }

    override fun onDestroy() {
        listFoodPageBinding = null;
        super.onDestroy()
    }
}