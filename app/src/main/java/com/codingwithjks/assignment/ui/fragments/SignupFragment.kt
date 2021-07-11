package com.codingwithjks.assignment.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.codingwithjks.assignment.R
import com.codingwithjks.assignment.data.model.Patient
import com.codingwithjks.assignment.databinding.FragmentSignupBinding
import com.codingwithjks.assignment.ui.viewmodel.MainViewModel
import com.codingwithjks.assignment.utils.showMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        moveToLoginPage()
        signup()
        return binding.root
    }

    private fun signup() {
        binding.apply {
            signup.setOnClickListener {
                if (!TextUtils.isEmpty(username.text.toString())
                    && !TextUtils.isEmpty(password.text.toString().trim())
                    && !TextUtils.isEmpty(email.text.toString().trim())
                ) {
                    lifecycleScope.launchWhenStarted {
                        mainViewModel.insert(
                            Patient(
                                username.text.toString().trim(),
                                email.text.toString().trim(),
                                password.text.toString().trim()
                            )
                        )
                        requireActivity().showMsg("Register successfully...")
                        val bundle = Bundle()
                        bundle.putString("username", username.text.toString().trim())
                        view?.findNavController()
                            ?.navigate(R.id.action_signupFragment_to_homeFragment, bundle)
                    }
                } else {
                    requireActivity().showMsg("please fill all the fields..")
                }
            }
        }
    }

    private fun moveToLoginPage() {
        binding.apply {
            signIn.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_signupFragment_to_loginFragment)
            }
        }
    }
}