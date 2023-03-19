package com.example.avivgroup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avivgroup.data.model.PropertyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/**
 * The MainViewModel.kt, shared viewModel to transect the data b/w fragments
 * @author Malik Dawar, malikdawar@hotmail.com
 */


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var _propertyModel = MutableLiveData<PropertyModel>()
    var propertyModelLiveData: LiveData<PropertyModel> = _propertyModel

    fun savePhotoModel(photo: PropertyModel) {
        _propertyModel.value = photo
    }
}
