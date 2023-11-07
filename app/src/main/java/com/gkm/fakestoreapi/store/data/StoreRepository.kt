package com.gkm.fakestoreapi.store.data

import com.gkm.fakestoreapi.store.network.UseRetrofit

class StoreRepository (){

    private val api = UseRetrofit()

    suspend fun getStore():StoreResponse{
        return api.getListStore()
    }
}