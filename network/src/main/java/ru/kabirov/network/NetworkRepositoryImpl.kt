package ru.kabirov.network

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import ru.kabirov.network.api.VacanciesApi
import ru.kabirov.network.api.dto.Base
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    val context: Context,
    private val vacanciesApi: VacanciesApi
) :
    NetworkRepository {
    override suspend fun getBaseDTO(): Base {
        val apiFilePath = "${context.filesDir.absolutePath}${File.separator}api.json"
        if (!File(apiFilePath).exists()) {
            vacanciesApi.downloadFile().body()?.let {
                saveFile(it, apiFilePath)
            }
        }

        return getBaseFromFile(apiFilePath)
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
}