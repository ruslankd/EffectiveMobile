package ru.kabirov.network.api.dto

import com.google.gson.annotations.SerializedName

data class Experience(
    @SerializedName("previewText") var previewText: String? = null,
    @SerializedName("text") var text: String? = null
)
