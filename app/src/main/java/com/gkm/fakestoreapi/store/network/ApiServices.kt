package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.LoginRequest
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.StoreResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @GET("products")
    suspend fun getStoreProducts(): Response<List<StoreResponse>>

    @POST("auth/login")
    suspend fun getLogin(@Body request: LoginRequest):Response<LoginResponse>
}