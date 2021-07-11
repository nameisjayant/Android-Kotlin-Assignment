package com.codingwithjks.assignment.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithjks.assignment.data.adapter.MedicineAdapter
import com.codingwithjks.assignment.databinding.FragmentHomeBinding
import com.codingwithjks.assignment.ui.viewmodel.MainViewModel
import com.codingwithjks.assignment.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment(),Listener {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var medicineAdapter: MedicineAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val username = arguments?.getString("username")
        binding.apply {
            greet.text = username?.let { currentTime(it) }
        }
        initRecyclerview()
        insertIntoRoom()
        fetchFromLocal()
        return binding.root
    }

    private fun fetchFromLocal() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDrugs()
                .catch {
                    requireActivity().showMsg("something went wrong..")
                }.collect {
                    medicineAdapter.submitList(it)
                }
        }
    }

    private fun insertIntoRoom() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.apiStates
                .collect {
                    when (it) {
                        is States.Failure -> {
                            Log.d("main", "${it.msg}")
                        }
                        is States.Success -> {
                            mainViewModel.insertDrugs(
                                it.data.problems[0].Diabetes[0].medications[0].medicationsClasses[0].className[0]
                                    .associatedDrug
                            )

                        }
                        is States.Empty -> {

                        }
                    }
                }
        }
    }

    private fun initRecyclerview() {
        medicineAdapter = MedicineAdapter(this)
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = medicineAdapter
            }
        }
    }

    override fun onClick(name: String, dose: String, strength: String) {
        val bundle = Bundle()
        bundle.putString("name",name)
        bundle.putString("dose",dose)
        bundle.putString("strength",strength)
        view?.findNavController()?.navigate(com.codingwithjks.assignment.R.id.action_homeFragment_to_detailFragment,bundle)
    }

}