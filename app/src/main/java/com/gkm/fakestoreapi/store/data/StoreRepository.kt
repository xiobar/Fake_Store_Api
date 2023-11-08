package com.gkm.fakestoreapi.store.data

import com.gkm.fakestoreapi.store.network.UseRetrofit
import javax.inject.Inject

class StoreRepository @Inject constructor(private val api:UseRetrofit){

    //private val api = UseRetrofit()

    suspend fun getStore():List<StoreResponse>{
        return api.getListStore()
    }
}