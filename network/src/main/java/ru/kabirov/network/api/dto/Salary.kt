package ru.kabirov.network.api.dto

import com.google.gson.annotations.SerializedName

data class Salary (
    @SerializedName("full" ) var full : String? = null
)
