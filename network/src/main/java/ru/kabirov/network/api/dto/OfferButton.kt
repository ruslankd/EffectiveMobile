package ru.kabirov.network.api.dto

import com.google.gson.annotations.SerializedName

data class OfferButton(
    @SerializedName("text") var text: String? = null,
)
