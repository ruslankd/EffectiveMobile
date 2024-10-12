package ru.kabirov.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class StoredVacancy(
    @PrimaryKey
    var id: String,
    var isFavorite: Boolean? = null,
    var lookingNumber: Int? = null,
    var title: String? = null,
    var town: String? = null,
    var company: String? = null,
    var experience: String? = null,
    var publishedDate: String? = null
)
