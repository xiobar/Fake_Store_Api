package com.gkm.fakestoreapi.store.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkm.fakestoreapi.store.ui.components.TextFieldView
import com.gkm.fakestoreapi.store.ui.components.TextView
import com.gkm.fakestoreapi.store.ui.components.TopAppBarViewBack
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ProductScreen(
    navigator: DestinationsNavigator,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBarViewBack(
            text = "Productos",
            navigation = { navigator.popBackStack() })

    }
    ) { paddingValues ->
        ListProduct(modifier = Modifier.padding(paddingValues), productViewModel)
    }
}

@Composable
fun ListProduct(modifier: Modifier, productViewModel: ProductViewModel) {
    val text:String by productViewModel.searchProduct.observeAsState(initial = "")
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextFieldView(
            text = text,
            textValue = { productViewModel.changedSearch(it) },
            label = { TextView(text = "Search") },
            modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun PreviewProducts() {
    ProductScreen(navigator = EmptyDestinationsNavigator)
}