package com.example.avivgroup.data.remote

import com.example.avivgroup.data.model.PropertyResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET(SERVICE_API)
    suspend fun loadProperties(): ApiResponse<PropertyResponse>

    companion object {
        const val SERVICE_API = "listings.json"
    }
}
