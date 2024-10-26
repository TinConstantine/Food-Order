package com.example.orderfood.models

import com.google.gson.annotations.SerializedName

data class PriceModel(
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("Value") var Value: String? = null
)