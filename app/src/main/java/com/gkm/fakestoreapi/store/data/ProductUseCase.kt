package com.gkm.fakestoreapi.store.data

import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductRepository){

    suspend operator fun invoke(token:String):List<ProductResponse>{
        return repository.getStore(token)
    }
}