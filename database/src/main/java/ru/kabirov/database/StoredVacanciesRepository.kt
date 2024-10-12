package ru.kabirov.database

import kotlinx.coroutines.flow.Flow
import ru.kabirov.network.api.dto.Vacancies

interface StoredVacanciesRepository {
    fun getAllVacancies(): Flow<List<Vacancies>>
    suspend fun getVacancy(vacancy: Vacancies): Vacancies?
    suspend fun insertVacancy(vacancy: Vacancies)
    suspend fun insertVacancyWithReplace(vacancy: Vacancies)
    suspend fun deleteVacancy(vacancy: Vacancies)
}