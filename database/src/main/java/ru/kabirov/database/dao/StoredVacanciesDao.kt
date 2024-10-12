package ru.kabirov.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kabirov.database.entity.StoredVacancy

@Dao
interface StoredVacanciesDao {

    @Query("SELECT * FROM vacancies;")
    fun getAllStoredVacancies(): Flow<List<StoredVacancy>>

    @Query("SELECT * FROM vacancies WHERE id = :id")
    suspend fun getVacancy(id: String): StoredVacancy?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancy(vacancies: StoredVacancy)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancyWithReplace(vacancies: StoredVacancy)

    @Delete
    suspend fun deleteVacancy(vacancy: StoredVacancy)
}