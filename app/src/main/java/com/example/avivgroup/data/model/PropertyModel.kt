package com.example.avivgroup.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertyResponse(
    @field:SerializedName("items")
    val hits: List<PropertyModel>? = null,
) : Parcelable

@Parcelize
data class PropertyModel(
    @Expose val id: Int,
    @Expose val bedrooms: String,
    @Expose val city: String,
    @Expose val url: String,
    @Expose val price: Double,
    @Expose val area: Float,
    @Expose val offerType: Int,
    @Expose val rooms: Int,
    @Expose val propertyType: String,
) : Parcelable
