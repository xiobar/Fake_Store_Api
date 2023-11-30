package com.gkm.fakestoreapi.store.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.logError.LogException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    //private val storeUseCase: StoreUseCase
) : ViewModel() {

    //private val storeUseCase: StoreUseCase = StoreUseCase()

   /* private val _getProducts = MutableStateFlow(emptyList<StoreResponse>())
    val getProducts: StateFlow<List<StoreResponse>> = _getProducts

    fun listProducts() {
        viewModelScope.launch {
            try {
                val products = storeUseCase(token = "")
                _getProducts.value = products
            } catch (e: LogException) {
                Log.e("ErrorList", "Error al obtener el listado: ", e)
            }
        }
    }*/
}