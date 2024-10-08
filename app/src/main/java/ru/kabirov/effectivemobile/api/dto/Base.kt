package ru.kabirov.effectivemobile.api.dto

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("offers") var offers: ArrayList<Offers> = arrayListOf(),
    @SerializedName("vacancies") var vacancies: ArrayList<Vacancies> = arrayListOf()
)
