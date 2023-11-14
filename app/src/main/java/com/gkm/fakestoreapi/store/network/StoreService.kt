package com.gkm.fakestoreapi.store.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoreService {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://186.68.94.114:8444/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getClient(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}

//url:http://186.68.94.114:8444/ publico
//url:http://192.168.1.46:8444/ interno