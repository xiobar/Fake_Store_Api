package com.gkm.fakestoreapi.store.data

class StoreUseCase {

    private val repository = StoreRepository()

    suspend operator fun invoke():List<StoreResponse>{
        return repository.getStore()
    }
}