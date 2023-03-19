package com.example.avivgroup.home

import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.PhotosDataSource
import com.example.avivgroup.data.repository.properties.PropertiesRepository
import com.example.avivgroup.data.usecases.FetchPropertiesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchPropertiesUseCaseTest {

    @MockK
    private lateinit var repository: PropertiesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given when the invoke in FetchPhotosUseCase is called then it fetch the photos`() =
        runBlocking {
            // Given
            val sut = FetchPropertiesUseCase(repository)
            val givenPhotos = PhotosDataSource.getPhotosList()

            // When
            coEvery { repository.loadProperties() }
                .returns(flowOf(DataState.success(givenPhotos)))

            // Invoke
            val photosListFlow = sut()

            // Then
            MatcherAssert.assertThat(photosListFlow, CoreMatchers.notNullValue())

            val photosListDataState = photosListFlow.first()
            MatcherAssert.assertThat(photosListDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                photosListDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java),
            )

            val photosList = (photosListDataState as DataState.Success).data
            MatcherAssert.assertThat(photosList, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(photosList.hits?.size, `is`(givenPhotos.hits?.size))
        }
}
