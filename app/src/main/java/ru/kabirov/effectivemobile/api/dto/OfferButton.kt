package ru.kabirov.effectivemobile.api.dto

import com.google.gson.annotations.SerializedName

data class OfferButton(
    @SerializedName("text") var text: String? = null,
)
