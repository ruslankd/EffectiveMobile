package ru.kabirov.network

import ru.kabirov.network.api.dto.Base

interface NetworkRepository {
    suspend fun getBaseDTO(): Base
}