package com.example.avivgroup.data

import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.model.PropertyResponse

object PropertyDataSource {

    fun propertyResponse(): PropertyResponse {
        return PropertyResponse(
            arrayListOf(
                PropertyModel(
                    id = 5352510,
                    bedrooms = 2,
                    city = "Koln",
                    url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                    price = 27100.0,
                    area = 302.02,
                    offerType = 1,
                    rooms = 4,
                    propertyType = "Maison - Villa",
                ),
            ),
        )
    }
}
