package com.example.avivgroup.data

import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.model.PropertyResponse

object PhotosDataSource {

    fun getPhotosList(): PropertyResponse {
        val hits = ArrayList<PropertyModel>()
        hits.add(propertyModel)
        return PropertyResponse(hits)
    }

    private val propertyModel: PropertyModel
        get() = PropertyModel(
            id = 5352510,
            type = "photo",
            previewURL = "https://cdn.pixabay.com/photo/2020/06/29/12/11/snail-5352510_150.jpg",
            largeImageURL = "https://pixabay.com/get/gf5528b88e1e5d56623b80f8249a24ef95939d08a2893fb71069a158def9954072cb9614c2e4a42bd01ef5c086c2e7edfb71e2e5d5a636710845b1e1e15557b26_1280.jpg",
            downloads = 27100,
            views = 30236,
            user = "Alexas_Fotos",
            userImageURL = "https://cdn.pixabay.com/user/2021/05/06/10-24-10-84_250x250.jpg"
        )

}