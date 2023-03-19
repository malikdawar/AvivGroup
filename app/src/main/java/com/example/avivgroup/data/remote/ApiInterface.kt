package com.example.avivgroup.data.remote

import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.data.model.PropertyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(SERVICE_API)
    suspend fun loadProperties(): ApiResponse<PropertyResponse>

    @GET(SINGLE_PROPERTY_SERVICE_API)
    suspend fun loadProperty(
        @Path("listingId") propertyId: Int,
    ): ApiResponse<PropertyModel>

    companion object {
        const val SERVICE_API = "listings.json"
        const val SINGLE_PROPERTY_SERVICE_API = "listings/{listingId}.json"
    }
}
