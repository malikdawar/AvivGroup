package com.example.avivgroup.data.usecases

import com.example.avivgroup.data.repository.singleproperty.PropertyRepository
import javax.inject.Inject

/**
 * A use-case to load the single property from API.
 * @author Malik Dawar
 */
class FetchPropertyUseCase @Inject constructor(
    private val repository: PropertyRepository,
) {
    suspend operator fun invoke(propertyId: Int) = repository.loadProperty(propertyId)
}
