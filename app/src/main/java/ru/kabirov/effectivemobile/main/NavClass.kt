package ru.kabirov.effectivemobile.main

import kotlinx.serialization.Serializable

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val badgeAmount: Int? = null
)

@Serializable
object Search
@Serializable
object Favorites
@Serializable
object Responses
@Serializable
object Messages
@Serializable
object Profile

@Serializable
object AppScaffold
@Serializable
data class VacancyDetail(val title: String)
