package com.gkm.fakestoreapi.store.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetorfitRes {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}