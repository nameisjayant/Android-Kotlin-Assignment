package com.codingwithjks.assignment.data.repository

import com.codingwithjks.assignment.data.dao.MedicineDao
import com.codingwithjks.assignment.data.dao.UserDao
import com.codingwithjks.assignment.data.model.AssociatedDrug
import com.codingwithjks.assignment.data.model.Medicine
import com.codingwithjks.assignment.data.model.Patient
import com.codingwithjks.assignment.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userDao: UserDao,
    private val medicineDao: MedicineDao,
    private val apiService: ApiService
) {

    fun getData():Flow<Medicine> = flow {
        emit(apiService.getData())
    }.flowOn(Dispatchers.IO)

    suspend fun insert(patient: Patient) = withContext(Dispatchers.IO) {
        userDao.insertUser(patient)
    }

    fun login(username: String, password: String): Flow<Patient> =
        userDao.login(username, password)

    suspend fun insertDrugs(data: List<AssociatedDrug>) =
        withContext(Dispatchers.IO) {
            medicineDao.insertDrugs(data)
        }

    fun getAllDrugs(): Flow<List<AssociatedDrug>> = medicineDao.getAllDrugs()

}