package com.example.orderfood.screens.Intro.IntroPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.orderfood.MainActivity
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentIntroPageBinding

class IntroPage : Fragment(R.layout.fragment_intro_page) {
    private var introPageBinding: FragmentIntroPageBinding? = null;
    private val binding get() = introPageBinding!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        introPageBinding = FragmentIntroPageBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setVariable()
        if((activity as MainActivity).mAuth.currentUser != null){
            view.findNavController().navigate(R.id.action_introPage_to_dashboardPage);
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setVariable() {
        binding.btnLogin.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_introPage_to_loginPage)
        }

        binding.btnSignup.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_introPage_to_signupPage)
        }
    }

    override fun onDestroy() {
        introPageBinding = null;
        super.onDestroy()
    }
}