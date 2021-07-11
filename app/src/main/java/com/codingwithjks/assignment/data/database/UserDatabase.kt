package com.codingwithjks.assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithjks.assignment.data.dao.MedicineDao
import com.codingwithjks.assignment.data.dao.UserDao
import com.codingwithjks.assignment.data.model.AssociatedDrug
import com.codingwithjks.assignment.data.model.Patient

@Database(entities = [Patient::class,AssociatedDrug::class],version = 4,exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao
    abstract fun medicineDao():MedicineDao
}