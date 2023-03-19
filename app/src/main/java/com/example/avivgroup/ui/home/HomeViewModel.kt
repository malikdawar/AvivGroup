package com.example.avivgroup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.usecases.FetchPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The HomeViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPropertiesUseCase: FetchPropertiesUseCase,
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _propertiesList = MutableLiveData<List<PropertyModel>>()
    var propertiesListLiveData: LiveData<List<PropertyModel>> = _propertiesList

    init {
        fetchProperties()
    }

    private fun fetchProperties() {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchPropertiesUseCase.invoke().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        dataState.data.hits?.let {
                            _propertiesList.postValue(it)
                        }
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }
}
