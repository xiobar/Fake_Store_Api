package com.gkm.fakestoreapi.store.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkm.fakestoreapi.logError.LogException
import com.gkm.fakestoreapi.store.data.ProductResponse
import com.gkm.fakestoreapi.store.data.ProductUseCase
import com.gkm.fakestoreapi.store.preference.AuthorizateCredentials
import com.gkm.fakestoreapi.store.preference.PreferenceDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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

    private var tokenProduct:String = ""
    fun setToken(token:String){
        tokenProduct = token
    }

    private fun getToken():String{
        return tokenProduct
    }

    fun changedSearch(searchProduct:String){
        _searchProduct.value = searchProduct
    }

    private val readAuthorizade: StateFlow<AuthorizateCredentials> =
        dataStore.readAuthorizate.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthorizateCredentials("")
        )


    fun listProducts(){
        viewModelScope.launch {
            try{
                _getProducts.value = productUseCase(getToken())
                Log.i("tokenp", "autor token ${getToken()}")
            }catch(e:LogException){
                Log.e("Errorlist", "Error al obtener el listado", e)
                Log.i("tokenpro", "autor token ${getToken()}")
            }
        }
    }
}