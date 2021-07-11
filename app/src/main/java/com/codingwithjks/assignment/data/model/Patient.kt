package com.codingwithjks.assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient")
data class Patient(
    val username: String?,
    val email: String?,
    val password: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
