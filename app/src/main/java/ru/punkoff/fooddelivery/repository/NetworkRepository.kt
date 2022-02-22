package ru.punkoff.fooddelivery.repository

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun getMenu(): Flow<NetworkState>
}