package com.gkm.fakestoreapi.store.data

import com.gkm.fakestoreapi.store.network.UseRetrofit
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api:UseRetrofit) {
    suspend fun getImage(image:String):String{
        return api.getImage(image)
    }
}