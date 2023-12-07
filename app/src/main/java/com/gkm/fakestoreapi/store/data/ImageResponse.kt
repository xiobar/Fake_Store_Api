package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class ImageResponse (
    @SerializedName("codProducto") val picture:String?=null
)