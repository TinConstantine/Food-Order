package com.example.orderfood.screens.Dashboard.DashboardPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderfood.MainActivity
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentDashboardPageBinding
import com.example.orderfood.screens.Dashboard.DashboardPage.adapter.BestFoodAdapter
import com.example.orderfood.screens.Dashboard.DashboardPage.adapter.CategoryAdapter
import com.example.orderfood.screens.Dashboard.DashboardPage.adapter.CustomAdapter
import com.google.firebase.auth.FirebaseAuth

class DashboardPage : Fragment(R.layout.fragment_dashboard_page) {
    private lateinit var dashboardViewmodel: DashboardViewmodel;
    private var dashboardPageBinding: FragmentDashboardPageBinding? = null;
    private val binding get() = dashboardPageBinding!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardPageBinding = FragmentDashboardPageBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dashboardViewmodel = DashboardViewmodel((activity as MainActivity).database);
        initLocation();
        initTime()
        initPrice()
        initBestFood()
        initCategory()
        setVariable()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setVariable() {
       binding.btnLogout.setOnClickListener{
           (activity as MainActivity).mAuth.signOut()
           Toast.makeText(requireActivity(), "You are sign out", Toast.LENGTH_LONG).show()
           it.findNavController().navigate(R.id.action_dashboardPage_to_introPage, null, NavOptions.Builder()
               .setPopUpTo(R.id.dashboardPage, true)
               .build())
       }

        binding.imgSearch.setOnClickListener{
            if(binding.edtSearch.text.trim().isEmpty()) return@setOnClickListener;
            val keyword = binding.edtSearch.text.toString();
            val direction = DashboardPageDirections.actionDashboardPageToListFoodPage(-1,keyword,keyword)
            it.findNavController().navigate(direction)
        }
        binding.imgBasket.setOnClickListener {
            it.findNavController().navigate(R.id.action_dashboardPage_to_cartPage);
        }
    }

    private fun initLocation(){
        dashboardViewmodel.getLocation();
        dashboardViewmodel.listLocationModel.observe(viewLifecycleOwner){
            list->
           val adapter: CustomAdapter.LocationAdapter = CustomAdapter.LocationAdapter(requireActivity(),list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerLocation.adapter = adapter;
        }
    }
    private fun initTime(){
        dashboardViewmodel.getTime();
        dashboardViewmodel.listTime.observe(viewLifecycleOwner){
                list->
            val adapter: CustomAdapter.TimeAdapter = CustomAdapter.TimeAdapter(requireActivity(),list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerTime.adapter = adapter;
        }
    }
    private fun initPrice(){
        dashboardViewmodel.getPrice();
        dashboardViewmodel.listPrice.observe(viewLifecycleOwner){
                list->
            val adapter: CustomAdapter.PriceAdapter = CustomAdapter.PriceAdapter(requireActivity(),list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerPrice.adapter = adapter;
        }
    }
    private fun initBestFood(){
        binding.progressBarBestFood.visibility = View.VISIBLE;
        dashboardViewmodel.getBestFood();
        dashboardViewmodel.listFood.observe(viewLifecycleOwner){ list->
            if(list.isNotEmpty()){
                binding.rvBestFood.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
                val adapter = BestFoodAdapter(requireActivity());
                adapter.different.submitList(list);
                binding.rvBestFood.adapter = adapter;
                binding.progressBarBestFood.visibility = View.GONE;
            }

        }
    }
    private fun initCategory(){
        binding.progressBarCategory.visibility = View.VISIBLE;
        dashboardViewmodel.getCategory();
        dashboardViewmodel.listCategory.observe(viewLifecycleOwner){ list->
            if(list.isNotEmpty()){
                binding.rvCategory.layoutManager = GridLayoutManager(requireActivity(), 4);
                val adapter = CategoryAdapter(requireActivity());
                adapter.different.submitList(list);
                binding.rvCategory.adapter = adapter;
                binding.progressBarCategory.visibility = View.GONE;
            }

        }
    }

    override fun onDestroy() {
        dashboardPageBinding = null;
        super.onDestroy()
    }

}