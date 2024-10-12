package ru.kabirov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kabirov.database.dao.StoredVacanciesDao
import ru.kabirov.database.entity.StoredVacancy

@Database(
    entities = [StoredVacancy::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storedVacanciesDao(): StoredVacanciesDao
}