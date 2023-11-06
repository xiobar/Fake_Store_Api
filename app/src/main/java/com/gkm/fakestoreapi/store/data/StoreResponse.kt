package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("id") val codigo:Int,
    @SerializedName("title") val titulo:String,
    @SerializedName("price") val precio:Double,
    @SerializedName("description") val descripcion:String,
    @SerializedName("category") val categoria:String,
)