package com.example.avivgroup.data.repository

import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.model.PropertyResponse
import kotlinx.coroutines.flow.Flow

/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [PropertiesRepositoryImpl] for implementation of this class to utilize Unsplash API.
 * @author Malik Dawar
 */
interface PropertiesRepository {
    suspend fun loadProperties(): Flow<DataState<PropertyResponse>>
}
