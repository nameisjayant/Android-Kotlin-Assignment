package com.codingwithjks.assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithjks.assignment.data.model.AssociatedDrug
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugs(data:List<AssociatedDrug>)

    @Query("SELECT * FROM associatedDrug")
    fun getAllDrugs():Flow<List<AssociatedDrug>>
}