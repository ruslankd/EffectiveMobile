package ru.kabirov.effectivemobile.api.dto

import com.google.gson.annotations.SerializedName

data class Salary (
    @SerializedName("full" ) var full : String? = null
)
