package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username") val name:String,
    @SerializedName("password") val password:String
)
