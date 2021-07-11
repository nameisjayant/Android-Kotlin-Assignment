package com.codingwithjks.assignment.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codingwithjks.assignment.data.dao.MedicineDao
import com.codingwithjks.assignment.data.dao.UserDao
import com.codingwithjks.assignment.data.model.AssociatedDrug
import com.codingwithjks.assignment.data.model.Patient
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest : TestCase() {

    private lateinit var db: UserDatabase
    private lateinit var userDao: UserDao
    private lateinit var medicineDao: MedicineDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .build()
        userDao = db.userDao()
        medicineDao = db.medicineDao()
    }

    @Test
    fun readAndWriteUser() = runBlocking {
        val patient = Patient("test", "test123@gmail.com", "12345")
        userDao.insertUser(patient)
        val data = userDao.login("test", "12345")
        assertThat(data.equals(patient)).isFalse()
    }

    @Test
    fun readAndWriteMedicine() = runBlocking {
        val data = listOf(
            AssociatedDrug("asprin","2","500 mg"),
            AssociatedDrug("abacavir","4","400 mg"),
        )
        val drugs = medicineDao.insertDrugs(data)
        val getData = medicineDao.getAllDrugs()
        assertThat(getData.equals(drugs)).isFalse()
    }

    @After
    fun close() {
        db.close()
    }

}