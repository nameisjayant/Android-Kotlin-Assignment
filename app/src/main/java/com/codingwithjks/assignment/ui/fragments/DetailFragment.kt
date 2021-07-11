package com.codingwithjks.assignment.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codingwithjks.assignment.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val mName = arguments?.getString("name")
        val mDose = arguments?.getString("dose")
        val mStrength = arguments?.getString("strength")
        binding.apply {
            name.text = "Name : $mName"
            dose.text = "Dose : $mDose"
            strength.text = "Strength : $mStrength"
        }
        return binding.root
    }
}