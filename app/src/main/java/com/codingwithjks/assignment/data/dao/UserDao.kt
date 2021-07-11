package com.codingwithjks.assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithjks.assignment.data.model.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(patient: Patient)

    @Query("SELECT * FROM patient WHERE username=:username AND password=:password")
    fun login(username:String,password:String):Flow<Patient>

}