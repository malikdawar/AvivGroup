package com.example.avivgroup.home

import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.PropertyDataSource
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
    fun `given when the invoke in loadProperties is called then it fetch the properties`() =
        runBlocking {
            // Given
            val sut = FetchPropertiesUseCase(repository)
            val givenProperties = PropertyDataSource.propertyResponse()

            // When
            coEvery { repository.loadProperties() }
                .returns(flowOf(DataState.success(givenProperties)))

            // Invoke
            val propertiesListFlow = sut()

            // Then
            MatcherAssert.assertThat(propertiesListFlow, CoreMatchers.notNullValue())

            val propertiesListDataState = propertiesListFlow.first()
            MatcherAssert.assertThat(propertiesListDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                propertiesListDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java),
            )

            val propertiesList = (propertiesListDataState as DataState.Success).data
            MatcherAssert.assertThat(propertiesList, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                propertiesList.properties?.size,
                `is`(givenProperties.properties?.size),
            )
        }
}
