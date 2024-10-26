package com.example.orderfood.models

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("Id")
    var Id: Int? = null,
    @SerializedName("ImagePath")
    var ImagePath: String?= null,
    var Name: String? = null,
)