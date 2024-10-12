package ru.kabirov.effectivemobile.search

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kabirov.database.StoredVacanciesRepositoryImpl
import ru.kabirov.network.NetworkRepositoryImpl
import ru.kabirov.network.api.dto.Offers
import ru.kabirov.network.api.dto.Vacancies
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val networkRepository: NetworkRepositoryImpl,
    private val storedVacancyRepository: StoredVacanciesRepositoryImpl
) : ViewModel() {

    private val _favoritesVacancies = MutableStateFlow<List<Vacancies>>(emptyList())
    val favoritesVacancies = _favoritesVacancies.asStateFlow()

    private val _vacancies = MutableStateFlow<List<Vacancies>>(emptyList())
    val vacancies = _vacancies.asStateFlow()

    private val vacanciesFlow = storedVacancyRepository.getAllVacancies()

    val offersState = mutableStateOf(listOf<Offers>())

    private val _events = MutableSharedFlow<SearchEvents>()
    val events: SharedFlow<SearchEvents> = _events

    private val _isAllVacancies = MutableStateFlow(false)
    val isAllVacancies: StateFlow<Boolean> = _isAllVacancies

    private val _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString

    init {
        viewModelScope.launch {
            launch {
                getVacanciesAndOffers()
            }
            launch {
                vacanciesFlow.collectLatest { items ->
                    _favoritesVacancies.tryEmit(items.filter { it.isFavorite ?: false }
                        .sortedBy { it.id })
                    _vacancies.tryEmit(items.sortedBy { it.id })
                }
            }
        }
    }

    companion object {
        const val DEFAULT_VACANCIES_COUNT = 3
    }

    fun getVacanciesCount(): Int =
        if (!isAllVacancies.value) {
            DEFAULT_VACANCIES_COUNT
        } else {
            vacancies.value.size
        }

    fun onOffersClick(link: String) {
        viewModelScope.launch {
            _events.emit(SearchEvents.ToBrowser(link))
        }
    }

    fun onLeadingIconClick() {
        if (isAllVacancies.value) {
            viewModelScope.launch {
                _isAllVacancies.emit(false)
            }
        }
    }

    fun onExpandBtnClick() {
        viewModelScope.launch {
            _isAllVacancies.emit(true)
        }
    }

    fun onFavoriteClick(vacancy: Vacancies) {
        viewModelScope.launch {
            storedVacancyRepository.insertVacancyWithReplace(vacancy.copy(isFavorite = vacancy.isFavorite?.not()))
        }
    }

    fun searchStringValueChange(searchString: String) {
        viewModelScope.launch {
            _searchString.emit(searchString)
        }
    }

    suspend fun getVacanciesAndOffers() {
        networkRepository.getBaseDTO().apply {
            vacancies.map { vacancy ->
                storedVacancyRepository.insertVacancy(vacancy)
            }
        }
    }

    fun getVacanciesString(num: Int = vacancies.value.size): String {
        val preLastDigit: Int = num % 100 / 10

        if (preLastDigit == 1) {
            return "$num вакансий"
        }

        return when (num % 10) {
            1 -> "$num вакансия"
            2, 3, 4 -> "$num вакансии"
            else -> "$num вакансий"
        }
    }

    fun getCurrentlyViewingString(num: Int): String {
        val preLastDigit: Int = num % 100 / 10

        if (preLastDigit == 1) {
            return "$num человек"
        }

        return when (num % 10) {
            1 -> "$num человек"
            2, 3, 4 -> "$num человека"
            else -> "$num человек"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateFormat(dateString: String?): String {
        dateString?.let {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd")
            val targetFormat = SimpleDateFormat(
                "d MMMM",
                Locale.Builder().setLanguage("ru").setScript("Cyrl").build()
            )
            val date = originalFormat.parse(it)
            date?.let {
                return targetFormat.format(date)
            }
        }
        return ""
    }
}