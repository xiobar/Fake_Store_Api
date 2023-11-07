package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UseRetrofit {

    val retorfit = RetorfitRes()

    suspend fun getListStore(): StoreResponse {
        return withContext(Dispatchers.IO) {
            val call = retorfit.getRetrofit().create(ApiServices::class.java).getStoreProducts()
            call.body() ?: StoreResponse(0, "", 0.0, "", "")
        }
    }
}