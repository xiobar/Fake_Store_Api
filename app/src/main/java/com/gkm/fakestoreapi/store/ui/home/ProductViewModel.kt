package com.gkm.fakestoreapi.store.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel:ViewModel() {

    private val _searchProduct = MutableLiveData<String>()
    val searchProduct:LiveData<String> = _searchProduct

    fun changedSearch(searchProduct:String){
        _searchProduct.value = searchProduct
    }
}