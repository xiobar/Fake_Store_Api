package com.gkm.fakestoreapi.store.data

import javax.inject.Inject

class StoreUseCase @Inject constructor(private val repository: StoreRepository){

    //private val repository = StoreRepository()

    suspend operator fun invoke(token:String):List<StoreResponse>{
        return repository.getStore(token)
    }
}