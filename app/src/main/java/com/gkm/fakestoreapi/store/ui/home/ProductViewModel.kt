package com.gkm.fakestoreapi.store.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.logError.LogException
import com.gkm.fakestoreapi.store.data.ProductResponse
import com.gkm.fakestoreapi.store.data.ProductUseCase
import com.gkm.fakestoreapi.store.preference.PreferenceDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val dataStore: PreferenceDataStore
):ViewModel() {

    private val _searchProduct = MutableLiveData<String>()
    val searchProduct:LiveData<String> = _searchProduct

    private val _getProducts = MutableLiveData(emptyList<ProductResponse>())
    val getProducts: LiveData<List<ProductResponse>> = _getProducts

    fun changedSearch(searchProduct:String){
        _searchProduct.value = searchProduct
    }


    val token = dataStore.readCredentials

    fun listProducts(){
        viewModelScope.launch {
            try{
                val products = productUseCase(token = token.toString())
                _getProducts.value = products
                Log.i("token", "autor token $token")
            }catch(e:LogException){
                Log.e("Errorlist", "Error al obtener el listado", e)
                Log.i("token", "autor token $token")
            }
        }
    }
}