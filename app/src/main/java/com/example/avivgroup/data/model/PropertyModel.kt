package com.example.avivgroup.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertyResponse(
    @field:SerializedName("items")
    val properties: List<PropertyModel>? = null,
) : Parcelable

@Parcelize
data class PropertyModel(
    @Expose val id: Int,
    @Expose val bedrooms: Int?,
    @Expose val city: String?,
    @Expose val url: String?,
    @Expose val price: Double?,
    @Expose val area: Double?,
    @Expose val offerType: Int?,
    @Expose val rooms: Int?,
    @Expose val propertyType: String?,
) : Parcelable
