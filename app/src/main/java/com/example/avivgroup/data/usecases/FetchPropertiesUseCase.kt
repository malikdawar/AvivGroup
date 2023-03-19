package com.example.avivgroup.data.usecases

import com.example.avivgroup.data.repository.properties.PropertiesRepository
import javax.inject.Inject

/**
 * A use-case to load the photos from Unsplash API.
 * @author Malik Dawar
 */
class FetchPropertiesUseCase @Inject constructor(
    private val repository: PropertiesRepository,
) {
    suspend operator fun invoke() = repository.loadProperties()
}
