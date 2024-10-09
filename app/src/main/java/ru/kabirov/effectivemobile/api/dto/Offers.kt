package ru.kabirov.effectivemobile.api.dto

import com.google.gson.annotations.SerializedName

data class Offers(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("button") var button: OfferButton? = null
)
