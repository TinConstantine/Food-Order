package com.example.orderfood.screens.Intro.SignupPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.orderfood.MainActivity
import com.example.orderfood.R
import com.example.orderfood.databinding.FragmentSignupPageBinding

class SignupPage : Fragment(R.layout.fragment_signup_page) {
    private var bindingSignupPage : FragmentSignupPageBinding? = null;
    private val binding get() = bindingSignupPage!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSignupPage = FragmentSignupPageBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setVariable()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setVariable(){
        binding.btnSignup.setOnClickListener { view ->
            val email = binding.edtEmail.text.toString();
            val password = binding.edtPassword.text.toString();
            if(password.length < 6){
                Toast.makeText(requireActivity(), "Your password must be 6 characters", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }
            (activity as MainActivity).mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()){task->
                    if(task.isComplete){
                        Log.i("Auth", "onComplete")
                        view.findNavController().navigate(R.id.action_signupPage_to_loginPage)
                    }
                else{
                        Log.i("Auth", "failure ${task.exception}")
                }
            }
        }

        binding.btnGoLogin.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_signupPage_to_loginPage)
        }
    }
    override fun onDestroy() {
        bindingSignupPage = null;
        super.onDestroy()
    }
}
