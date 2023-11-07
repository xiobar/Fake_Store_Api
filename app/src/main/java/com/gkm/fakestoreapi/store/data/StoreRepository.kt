package com.gkm.fakestoreapi.store.data

import com.gkm.fakestoreapi.store.network.UseRetrofit

class StoreRepository (){

    private val api = UseRetrofit()

    suspend fun getStore():List<StoreResponse>{
        return api.getListStore()
    }
}