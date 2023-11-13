package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("nombre") val seller:String,
    @SerializedName("token") val token:String,
    @SerializedName("vendedorID") val SellerID:String
)