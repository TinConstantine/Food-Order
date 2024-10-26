package com.example.orderfood.screens.Intro.LoginPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.orderfood.MainActivity
import com.example.orderfood.R
import com.example.orderfood.compoment.LoadingStack
import com.example.orderfood.databinding.FragmentLoginPageBinding

class LoginPage : Fragment(R.layout.fragment_login_page) {
    private var bindingLoginPage: FragmentLoginPageBinding? = null;
    private val binding get() = bindingLoginPage!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLoginPage = FragmentLoginPageBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setVariable();
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setVariable() {
        binding.btnLogin.setOnClickListener { view ->
            val email = binding.edtEmail.text.toString();
            val password = binding.edtPassword.text.toString();
            if (password.isNotEmpty() || email.isNotEmpty()) {
                val loadingStack: LoadingStack = LoadingStack(requireActivity());
                loadingStack.show()
                (activity as MainActivity).mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireActivity(),
                                "Login successfully",
                                Toast.LENGTH_SHORT
                            ).show();
                            loadingStack.cancel()
                            view.findNavController().navigate(
                                R.id.action_loginPage_to_dashboardPage,
                                null,  NavOptions.Builder().setPopUpTo(R.id.introPage, true).build()
                            );
                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Wrong email or password",
                                Toast.LENGTH_SHORT
                            ).show();
                            Log.i("Auth", "failure ${task.exception}")
                        }

                    }
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Please fill email and password",
                    Toast.LENGTH_SHORT
                ).show();
                return@setOnClickListener;
            }


        }
        binding.btnGoToSignup.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginPage_to_signupPage)
        }

    }

    override fun onDestroy() {
        bindingLoginPage = null;
        super.onDestroy()
    }
}