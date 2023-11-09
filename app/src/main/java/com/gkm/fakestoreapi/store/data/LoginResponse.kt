package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token:String
)