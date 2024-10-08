package ru.kabirov.effectivemobile.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

interface VacanciesApi {
    @Streaming
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun downloadFile(): Response<ResponseBody>
}