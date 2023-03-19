package com.example.avivgroup.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.avivgroup.TestCoroutineRule
import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.PhotosDataSource
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.usecases.FetchPropertiesUseCase
import com.example.avivgroup.ui.home.ContentState
import com.example.avivgroup.ui.home.HomeUiState
import com.example.avivgroup.ui.home.HomeViewModel
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
    lateinit var fetchPopularPhotosUsecase: FetchPropertiesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, popular photos are fetched`() = runBlocking {
        // Given
        val givenPhotos = PhotosDataSource.getPhotosList()
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val photosListObserver = mockk<Observer<List<PropertyModel>>>(relaxed = true)

        // When
        coEvery { fetchPopularPhotosUsecase.invoke(any(), any(), any()) }
            .returns(flowOf(DataState.success(givenPhotos)))

        // Invoke
        sut = HomeViewModel(fetchPopularPhotosUsecase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.propertiesListLiveData.observeForever(photosListObserver)

        // Then
        coVerify(exactly = 1) { fetchPopularPhotosUsecase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { photosListObserver.onChanged(match { it.size == givenPhotos.hits?.size }) }
    }
}
