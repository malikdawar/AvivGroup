package com.example.avivgroup.data.repository.singleproperty

import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.model.PropertyResponse
import kotlinx.coroutines.flow.Flow

/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [PropertyRepositoryImpl] for implementation of this class to utilize Unsplash API.
 * @author Malik Dawar
 */
interface PropertyRepository {
    suspend fun loadProperty(propertyId: Int): Flow<DataState<PropertyModel>>
}
