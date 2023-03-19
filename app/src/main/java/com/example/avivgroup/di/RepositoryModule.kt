package com.example.avivgroup.di

import com.example.avivgroup.data.remote.ApiInterface
import com.example.avivgroup.data.repository.PropertiesRepository
import com.example.avivgroup.data.repository.PropertiesRepositoryImpl
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
    fun provideImagineRepository(apiService: ApiInterface): PropertiesRepository {
        return PropertiesRepositoryImpl(apiService)
    }
}
