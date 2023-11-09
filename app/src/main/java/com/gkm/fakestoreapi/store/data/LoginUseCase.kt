package com.gkm.fakestoreapi.store.data

import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository){

    suspend operator fun invoke(user:String, password:String):LoginResponse{
        return repository.getLogin(user, password)
    }
}