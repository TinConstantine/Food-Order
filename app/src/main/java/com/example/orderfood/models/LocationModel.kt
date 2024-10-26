package com.example.orderfood.models

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("loc") var loc: String? = null
)

