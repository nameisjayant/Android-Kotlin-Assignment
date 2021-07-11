package com.codingwithjks.assignment.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


data class Medicine(
    val problems: List<Problems>
)

data class Problems(
    val Diabetes: List<Diabetes>
)

data class Diabetes(
    val medications: List<Medications>
)

data class Medications(
    val medicationsClasses: List<MedicationsClasses>
)

data class MedicationsClasses(
    val className: List<ClassName>
)

data class ClassName(
    val associatedDrug: List<AssociatedDrug>
)

@Entity(tableName = "associatedDrug", indices = [Index(value = ["name"],unique = true)])
data class AssociatedDrug(
    @PrimaryKey
    val name: String,
    val dose: String,
    val strength: String
) {
}