package ru.kabirov.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.kabirov.network.NetworkRepositoryImpl
import ru.kabirov.network.api.VacanciesApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
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

    @Provides
    @Singleton
    fun provideNetworkRepositoryImpl(@ApplicationContext context: Context, vacanciesApi: VacanciesApi) =
        NetworkRepositoryImpl(context, vacanciesApi)
}