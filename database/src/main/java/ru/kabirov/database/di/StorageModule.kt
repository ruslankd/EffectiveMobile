package ru.kabirov.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kabirov.database.AppDatabase
import ru.kabirov.database.StoredVacanciesRepositoryImpl
import ru.kabirov.database.dao.StoredVacanciesDao
import ru.kabirov.database.mapping.DefaultDataMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    @Singleton
    fun provideDefaultDataMapper() = DefaultDataMapper()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "effective_mobile.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideStoredVacanciesDao(appDatabase: AppDatabase) = appDatabase.storedVacanciesDao()

    @Provides
    @Singleton
    fun provideStoredVacanciesRepositoryImpl(
        storedVacanciesDao: StoredVacanciesDao,
        defaultDataMapper: DefaultDataMapper
    ): StoredVacanciesRepositoryImpl =
        StoredVacanciesRepositoryImpl(storedVacanciesDao, defaultDataMapper)
}