package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.logError.LogException
import com.gkm.fakestoreapi.store.data.LoginRequest
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseRetrofit @Inject constructor(private val apiServices: ApiServices) {

    //private val retrofit = RetorfitRes()

    suspend fun getListStore(): List<StoreResponse> {
        return withContext(Dispatchers.IO) {
            val call = apiServices.getStoreProducts()
            if (call.isSuccessful) {
                call.body() ?: emptyList()
            } else {
                throw LogException("Error en la listado. Código: ${call.code()}")
                //emptyList()
            }
        }
    }

    suspend fun getLogin(user: String, password: String): LoginResponse {

        return withContext(Dispatchers.IO) {
            val response = apiServices.getLogin(LoginRequest(user, password))
            if (response.isSuccessful) {
                response.body() ?: LoginResponse("")
            } else {
                throw LogException("Error en la solicitud de inicio de sesion. Código: ${response.code()}")
            }

        }
    }

}