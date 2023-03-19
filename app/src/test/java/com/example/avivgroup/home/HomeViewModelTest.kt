package com.example.avivgroup.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.PropertyDataSource
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.usecases.FetchPropertiesUseCase
import com.example.avivgroup.ui.home.ContentState
import com.example.avivgroup.ui.home.HomeUiState
import com.example.avivgroup.ui.home.HomeViewModel
import com.example.avivgroup.utils.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    // Subject under test
    private lateinit var sut: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @MockK
    lateinit var fetchPropertiesUseCase: FetchPropertiesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, all properties are fetched`() = runBlocking {
        // Given
        val propertyResponse = PropertyDataSource.propertyResponse()
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val propertiesListObserver = mockk<Observer<List<PropertyModel>>>(relaxed = true)

        // When
        coEvery { fetchPropertiesUseCase.invoke() }
            .returns(flowOf(DataState.success(propertyResponse)))

        // Invoke
        sut = HomeViewModel(fetchPropertiesUseCase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.propertiesListLiveData.observeForever(propertiesListObserver)

        // Then
        coVerify(exactly = 1) { fetchPropertiesUseCase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { propertiesListObserver.onChanged(match { it.size == propertyResponse.properties?.size }) }
    }
}
