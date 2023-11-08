package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.store.data.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseRetrofit @Inject constructor(private val apiServices: ApiServices) {

    //private val retrofit = RetorfitRes()

    suspend fun getListStore(): List<StoreResponse> {
        return withContext(Dispatchers.IO) {
            val call = apiServices.getStoreProducts()
            call.body() ?: emptyList()
        }
    }
}