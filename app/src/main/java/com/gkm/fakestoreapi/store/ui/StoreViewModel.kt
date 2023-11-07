package com.gkm.fakestoreapi.store.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.store.data.StoreResponse
import com.gkm.fakestoreapi.store.data.StoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreViewModel: ViewModel() {
    private val storeUseCase: StoreUseCase = StoreUseCase()

    private val _getProducts = MutableStateFlow(emptyList<StoreResponse>())
    val getProducts:StateFlow<List<StoreResponse>> = _getProducts

    fun listProducts(){
        viewModelScope.launch {
            try{
                val products = storeUseCase()
                _getProducts.value = listOf(products)
            }catch (e:Exception){
                throw e
            }
        }
    }
}