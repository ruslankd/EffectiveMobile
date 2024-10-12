package ru.kabirov.database.mapping

import ru.kabirov.database.entity.StoredVacancy
import ru.kabirov.network.api.dto.Address
import ru.kabirov.network.api.dto.Experience
import ru.kabirov.network.api.dto.Vacancies

class DefaultDataMapper : DataMapper {
    override fun networkToStorage(networkVacancy: Vacancies) = with(networkVacancy) {
        StoredVacancy(
            id = id ?: "",
            isFavorite = isFavorite,
            lookingNumber = lookingNumber,
            title = title,
            town = address?.town,
            company = company,
            experience = experience?.previewText,
            publishedDate = publishedDate
        )
    }

    override fun storageToNetwork(storedVacancy: StoredVacancy) = with(storedVacancy) {
        Vacancies(
            id = id,
            isFavorite = isFavorite,
            lookingNumber = lookingNumber,
            title = title,
            address = Address(town = town),
            company = company,
            experience = Experience(previewText = experience),
            publishedDate = publishedDate
        )
    }
}