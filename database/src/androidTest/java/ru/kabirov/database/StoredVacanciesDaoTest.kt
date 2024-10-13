package ru.kabirov.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.kabirov.database.dao.StoredVacanciesDao
import ru.kabirov.database.entity.StoredVacancy
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class StoredVacanciesDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: StoredVacanciesDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.storedVacanciesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insertWithoutReplace() = runBlocking {
        var storedVacancy = StoredVacancy(id = "123", isFavorite = false)
        dao.insertVacancy(storedVacancy)
        storedVacancy = storedVacancy.copy(isFavorite = true)
        dao.insertVacancy(storedVacancy)
        dao.getVacancy(storedVacancy.id)?.let {
            storedVacancy = it
        }
        assertEquals(storedVacancy.isFavorite, false)
    }

    @Test
    fun test_insertWithReplace() = runBlocking {
        var storedVacancy = StoredVacancy(id = "123", isFavorite = false)
        dao.insertVacancy(storedVacancy)
        storedVacancy = storedVacancy.copy(isFavorite = true)
        dao.insertVacancyWithReplace(storedVacancy)
        dao.getVacancy(storedVacancy.id)?.let {
            storedVacancy = it
        }
        assertEquals(storedVacancy.isFavorite, true)
    }
}