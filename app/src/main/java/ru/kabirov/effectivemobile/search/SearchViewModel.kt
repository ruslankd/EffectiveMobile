package ru.kabirov.effectivemobile.search

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import ru.kabirov.effectivemobile.api.VacanciesApi
import ru.kabirov.effectivemobile.api.dto.Base
import ru.kabirov.effectivemobile.main.logd
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val vacanciesApi: VacanciesApi
) : ViewModel() {
    val baseApiObject = mutableStateOf(Base())

    private val _events = MutableSharedFlow<SearchEvents>()
    val events: SharedFlow<SearchEvents> = _events

    private val _isAllVacancies = MutableStateFlow(false)
    val isAllVacancies: StateFlow<Boolean> = _isAllVacancies

    private val _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString

    init {
        getVacanciesAndOffers()
    }

    companion object {
        const val DEFAULT_VACANCIES_COUNT = 3
    }


    fun setAllVacancies(enabled: Boolean) {
        _isAllVacancies.value = enabled
    }

    fun getVacanciesCount(): Int =
        if (!isAllVacancies.value) {
            DEFAULT_VACANCIES_COUNT
        } else {
            baseApiObject.value.vacancies.size
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

    fun searchStringValueChange(searchString: String) {
        viewModelScope.launch {
            _searchString.emit(searchString)
        }
    }

    fun getVacanciesAndOffers() {
        viewModelScope.launch {
            val apiFilePath = "${context.filesDir.absolutePath}${File.separator}api.json"
            if (!File(apiFilePath).exists()) {
                vacanciesApi.downloadFile().body()?.let {
                    context.logd("download file")
                    saveFile(it, apiFilePath)
                }
            }
            baseApiObject.value = getBaseFromFile(apiFilePath)
        }
    }

    private fun saveFile(body: ResponseBody, path: String): String {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return path
        } catch (e: Exception) {
            Log.e("saveFile", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }

    private fun getBaseFromFile(apiFilePath: String): Base {
        val fis = FileInputStream(apiFilePath)
        return Gson().fromJson(
            fis.bufferedReader().use { it.readText() },
            object : TypeToken<Base>() {}.type
        )
    }

    fun getVacanciesString(num: Int = baseApiObject.value.vacancies.size): String {
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