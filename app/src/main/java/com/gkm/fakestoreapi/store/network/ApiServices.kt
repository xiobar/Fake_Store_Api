package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.ImageResponse
import com.gkm.fakestoreapi.store.data.LoginRequest
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @GET("Productos")
    suspend fun getListProducts(@Header("Authorization") token:String): Response<List<ProductResponse>>

    @POST("Login")
    suspend fun getLogin(@Body request: LoginRequest):Response<LoginResponse>

    @GET("Productos/{codProducto}.jpg")
    fun getProductoImagen(@Path("codProducto")codProducto:String):Response<ImageResponse>
}