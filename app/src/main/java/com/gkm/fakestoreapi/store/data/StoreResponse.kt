package com.gkm.fakestoreapi.store.data

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("id") val id:String,
    @SerializedName("codigo") val code:String,
    @SerializedName("nombre") val name:String,
    @SerializedName("marca") val mark:String,
    @SerializedName("empaque") val packing:String,
    @SerializedName("stock") val stock:String,
    @SerializedName("costo") val cost:Double,
    @SerializedName("precio") val price:Double,
    @SerializedName("factor") val factor:Int,
    @SerializedName("indice") val index:String,
    @SerializedName("foto") val image:String,
)