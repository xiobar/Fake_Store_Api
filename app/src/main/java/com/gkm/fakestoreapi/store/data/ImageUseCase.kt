package com.gkm.fakestoreapi.store.data

import javax.inject.Inject

class ImageUseCase @Inject constructor(private val repository: ImageRepository) {

    suspend operator fun invoke(image:String):ByteArray{
        return repository.getImage(image)
    }
}