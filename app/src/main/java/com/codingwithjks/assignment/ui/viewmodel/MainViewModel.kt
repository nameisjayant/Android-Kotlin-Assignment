package com.codingwithjks.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjks.assignment.data.model.AssociatedDrug
import com.codingwithjks.assignment.data.model.Medicine
import com.codingwithjks.assignment.data.model.Patient
import com.codingwithjks.assignment.data.repository.MainRepository
import com.codingwithjks.assignment.utils.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _apiStates:MutableStateFlow<States> = MutableStateFlow(States.Empty)
    val apiStates:StateFlow<States> = _apiStates
    init {
        getData()
    }
    fun getData() = viewModelScope.launch {
        mainRepository.getData()
            .catch { e->
                _apiStates.value = States.Failure(e)
            }
            .collect { data->
                _apiStates.value = States.Success(data)
            }
    }

    fun insert(patient: Patient) = viewModelScope.launch {
        mainRepository.insert(patient)
    }

    fun login(username: String, password: String): Flow<Patient> =
        mainRepository.login(username, password)

    fun insertDrugs(data: List<AssociatedDrug>) = viewModelScope.launch {
        mainRepository.insertDrugs(data)
    }

    fun getAllDrugs() = mainRepository.getAllDrugs()

}