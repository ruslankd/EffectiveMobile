package ru.kabirov.database.mapping

import ru.kabirov.database.entity.StoredVacancy
import ru.kabirov.network.api.dto.Vacancies

interface DataMapper {
    fun networkToStorage(networkVacancy: Vacancies): StoredVacancy
    fun storageToNetwork(storedVacancy: StoredVacancy) : Vacancies
}