package com.gkm.fakestoreapi.store.network

import android.util.Log
import com.gkm.fakestoreapi.store.data.LoginRequest
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseRetrofit @Inject constructor(private val apiServices: ApiServices) {

    //private val retrofit = RetorfitRes()

    suspend fun getListStore(): List<StoreResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val call = apiServices.getStoreProducts()
                call.body() ?: emptyList()
            }
        }catch (e:Exception){
            Log.i("ErrorConnect", "Error al conectar: $e")
            emptyList()
        }
    }

    suspend fun getLogin(user:String, password:String):LoginResponse{
        return try{
            withContext(Dispatchers.IO){
                val response = apiServices.getLogin(LoginRequest(user, password))
                response.body()?:LoginResponse("")
            }
        }catch (e:Exception){
            throw e
        }
    }
}