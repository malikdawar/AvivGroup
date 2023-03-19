package com.example.avivgroup.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avivgroup.data.DataState
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.usecases.FetchPropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchPropertyUseCase: FetchPropertyUseCase,
) : ViewModel() {

    private var _propertyModel = MutableLiveData<PropertyModel>()
    var propertyModelLiveData: LiveData<PropertyModel> = _propertyModel

    private var _uiState = MutableLiveData<DetailsUiState>()
    var uiStateLiveData: LiveData<DetailsUiState> = _uiState

    fun fetchProperty(propertyId: Int) {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchPropertyUseCase.invoke(propertyId).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        dataState.data.let {
                            _propertyModel.value = it
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
