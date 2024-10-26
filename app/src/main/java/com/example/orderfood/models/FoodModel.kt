package com.example.orderfood.models
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodModel(
    @SerializedName("BestFood") var BestFood: Boolean? = null,
    @SerializedName("CategoryId") var CategoryId: Int? = null,
    @SerializedName("Description") var Description: String? = null,
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("ImagePath") var ImagePath: String? = null,
    @SerializedName("LocationId") var LocationId: Int? = null,
    @SerializedName("Price") var Price: Double? = null,
    @SerializedName("PriceId") var PriceId: Int? = null,
    @SerializedName("Star") var Star: Double? = null,
    @SerializedName("TimeId") var TimeId: Int? = null,
    @SerializedName("TimeValue") var TimeValue: Int? = null,
    @SerializedName("Title") var Title: String? = null,
    var numberInCart: Int? = null

): Serializable

// must use Serializable or Parcelable to use navigation args