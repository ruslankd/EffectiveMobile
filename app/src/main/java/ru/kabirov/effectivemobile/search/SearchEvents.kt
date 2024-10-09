package ru.kabirov.effectivemobile.search

sealed class SearchEvents {
    data class ToBrowser(val link: String): SearchEvents()
}