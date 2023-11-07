package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.StoreResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("products")
    suspend fun getStoreProducts(): Response<List<StoreResponse>>
}