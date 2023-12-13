package com.gkm.fakestoreapi.store.network

import com.gkm.fakestoreapi.logError.LogException
import com.gkm.fakestoreapi.store.data.LoginRequest
import com.gkm.fakestoreapi.store.data.LoginResponse
import com.gkm.fakestoreapi.store.data.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class UseRetrofit @Inject constructor(private val apiServices: ApiServices) {

    //private val retrofit = RetorfitRes()

    suspend fun getListStore(token:String): List<ProductResponse> {
        return withContext(Dispatchers.IO) {
            val call = apiServices.getListProducts(token)
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
                response.body() ?: LoginResponse("","","")
            } else {
                throw LogException("Error en la solicitud de inicio de sesion. Código: ${response.code()}")
            }

        }
    }

    suspend fun getImage(image:String):String{
        return withContext(Dispatchers.IO){
            try{
                val responseBody = apiServices.getProductoImagen(image).execute().body()
                responseBody?.string()?:String()
            }catch (e:IOException){
                "Error al devolver la imagen: ${e.message}"
            }catch (e:LogException){
                "Error desconocido al acceder: ${e.message}"
            }

        }
    }
}