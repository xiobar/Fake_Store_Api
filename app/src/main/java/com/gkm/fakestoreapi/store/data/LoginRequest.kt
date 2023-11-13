package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("usuario") val user:String,
    @SerializedName("password") val password:String
)
