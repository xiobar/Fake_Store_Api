package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UseRetrofit {

    private val retrofit = RetorfitRes()

    suspend fun getListStore(): List<StoreResponse> {
        return withContext(Dispatchers.IO) {
            val call = retrofit.getRetrofit().create(ApiServices::class.java).getStoreProducts()
            call.body() ?: emptyList()
        }
    }
}