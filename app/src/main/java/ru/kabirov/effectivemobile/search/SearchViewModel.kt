package ru.kabirov.effectivemobile.search

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import ru.kabirov.effectivemobile.api.VacanciesApi
import ru.kabirov.effectivemobile.api.dto.Offers
import ru.kabirov.effectivemobile.api.dto.Vacancies
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val vacanciesApi: VacanciesApi
) : ViewModel() {
    val vacancies by mutableStateOf(arrayListOf<Vacancies>())
    val offers by mutableStateOf(arrayListOf<Offers>())

    init {
        getVacanciesAndOffers()
    }

    fun getVacanciesAndOffers() {
        viewModelScope.launch {
            vacanciesApi.downloadFile().body()?.let {
                saveFile(it, "${context.filesDir.absolutePath}${File.separator}api.json")
            }
        }
    }

    private fun saveFile(body: ResponseBody, path: String): String {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            //val file = File(getCacheDir(), "cacheFileAppeal.srl")
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
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
}