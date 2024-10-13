package ru.kabirov.effectivemobile.search

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.kabirov.database.StoredVacanciesRepositoryImpl
import ru.kabirov.network.NetworkRepositoryImpl
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {

    private lateinit var vm: SearchViewModel

    @Inject
    lateinit var networkRepositoryImpl: NetworkRepositoryImpl

    @Inject
    lateinit var storedVacanciesRepositoryImpl: StoredVacanciesRepositoryImpl

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        vm = SearchViewModel(
            networkRepository = networkRepositoryImpl,
            storedVacancyRepository = storedVacanciesRepositoryImpl
        )
    }

    @Test
    fun test_getVacanciesString() {
        val map = mapOf(
            0 to "вакансий",
            1 to "вакансия",
            2 to "вакансии",
            3 to "вакансии",
            5 to "вакансий",
            15 to "вакансий",
            111 to "вакансий",
            400 to "вакансий",
            1001 to "вакансия",
            3012 to "вакансий",
        )
        map.forEach { (i, s) ->
            assertEquals("$i $s", vm.getVacanciesString(i))
        }
    }

    @Test
    fun test_getCurrentlyViewingString() {
        val map = mapOf(
            0 to "человек",
            1 to "человек",
            2 to "человека",
            3 to "человека",
            5 to "человек",
            15 to "человек",
            111 to "человек",
            400 to "человек",
            1001 to "человек",
            3012 to "человек",
        )
        map.forEach { (i, s) ->
            assertEquals("$i $s", vm.getCurrentlyViewingString(i))
        }
    }

    @Test
    fun test_getDateFormat() {
        // когда?
        val map = mapOf(
            "2020-06-01" to "1 июня",
            "2024-08-31" to "31 августа",
            "2020-02-29" to "29 февраля",
            "2020-01-02" to "2 января",
            "2020-04-10" to "10 апреля",
            null to ""
        )
        map.forEach { (i, s) ->
            assertEquals(s, vm.getDateFormat(i))
        }
    }
}