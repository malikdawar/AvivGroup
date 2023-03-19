package com.example.avivgroup.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avivgroup.data.model.PropertyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private var _propertyModel = MutableLiveData<PropertyModel>()
    var propertyModelLiveData: LiveData<PropertyModel> = _propertyModel

    fun initPhotoModelFromSharedViewMOdel(photo: PropertyModel) {
        _propertyModel.value = photo
    }
}
