package com.codingwithjks.assignment.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.codingwithjks.assignment.R
import com.codingwithjks.assignment.databinding.FragmentLoginBinding
import com.codingwithjks.assignment.ui.viewmodel.MainViewModel
import com.codingwithjks.assignment.utils.showMsg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        moveToSignupPage()
        login()
        return binding.root
    }

    @InternalCoroutinesApi
    private fun login() {
        binding.apply {
            login.setOnClickListener {
                if (!TextUtils.isEmpty(username.text.toString().trim())
                    && !TextUtils.isEmpty(password.text.toString().trim())
                ) {
                    lifecycleScope.launchWhenStarted {
                        mainViewModel.login(
                            username.text.toString().trim(),
                            password.text.toString().trim()
                        )
                            .catch {
                                requireActivity().showMsg("something went wrong..")
                            }.collect {
                                if (it != null){
                                    val bundle = Bundle()
                                    bundle.putString("username",it.username)
                                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment,bundle)
                                    requireActivity().showMsg("logged successfully....")
                                    Log.d("main", "$it")
                                }else{
                                    requireActivity().showMsg("wrong username and password")
                                }

                            }
                    }
                } else {
                    requireActivity().showMsg("please fill all the fields..")
                }
            }
        }
    }

    private fun moveToSignupPage() {
        binding.apply {
            signup.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_loginFragment_to_signupFragment)
            }
        }
    }
}