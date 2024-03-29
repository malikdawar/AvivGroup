package com.example.avivgroup.di

import com.example.avivgroup.data.remote.ApiInterface
import com.example.avivgroup.data.repository.properties.PropertiesRepository
import com.example.avivgroup.data.repository.properties.PropertiesRepositoryImpl
import com.example.avivgroup.data.repository.singleproperty.PropertyRepository
import com.example.avivgroup.data.repository.singleproperty.PropertyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePropertiesRepository(apiService: ApiInterface): PropertiesRepository {
        return PropertiesRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providePropertyRepository(apiService: ApiInterface): PropertyRepository {
        return PropertyRepositoryImpl(apiService)
    }
}
