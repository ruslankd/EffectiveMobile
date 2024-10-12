package ru.kabirov.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kabirov.database.dao.StoredVacanciesDao
import ru.kabirov.database.mapping.DataMapper
import ru.kabirov.network.api.dto.Vacancies
import javax.inject.Inject

class StoredVacanciesRepositoryImpl @Inject constructor(
    private val vacanciesDao: StoredVacanciesDao,
    private val dataMapper: DataMapper
) :
    StoredVacanciesRepository {
    override fun getAllVacancies(): Flow<List<Vacancies>> =
        vacanciesDao.getAllStoredVacancies().map { it.map { dataMapper.storageToNetwork(it) } }

    override suspend fun getVacancy(vacancy: Vacancies) =
        vacanciesDao.getVacancy(vacancy.id ?: "")?.let {
            dataMapper.storageToNetwork(it)
        }

    override suspend fun insertVacancy(vacancy: Vacancies) {
        vacanciesDao.insertVacancy(dataMapper.networkToStorage(vacancy))
    }

    override suspend fun insertVacancyWithReplace(vacancy: Vacancies) {
        vacanciesDao.insertVacancyWithReplace(dataMapper.networkToStorage(vacancy))
    }

    override suspend fun deleteVacancy(vacancy: Vacancies) {
        vacanciesDao.deleteVacancy(dataMapper.networkToStorage(vacancy))
    }
}