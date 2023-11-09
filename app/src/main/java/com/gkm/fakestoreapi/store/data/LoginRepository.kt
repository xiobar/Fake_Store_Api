package com.gkm.fakestoreapi.store.data

import com.gkm.fakestoreapi.store.network.UseRetrofit
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api:UseRetrofit){

    suspend fun getLogin(user:String, password:String):LoginResponse{
        return api.getLogin(user, password)
    }

}