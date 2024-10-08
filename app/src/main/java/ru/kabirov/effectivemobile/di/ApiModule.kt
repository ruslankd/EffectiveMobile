package ru.kabirov.effectivemobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.kabirov.effectivemobile.api.VacanciesApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun providesBaseUrl(): String =
        "https://drive.usercontent.google.com/u/0/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        )
        .build()

    @Provides
    @Singleton
    fun provideVacanciesApi(retrofit: Retrofit): VacanciesApi =
        retrofit.create(VacanciesApi::class.java)
}