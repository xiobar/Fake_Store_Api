package com.gkm.fakestoreapi.store.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkm.fakestoreapi.store.data.ProductResponse
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
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    Scaffold(topBar = {
        TopAppBarViewBack(
            text = "Productos",
            navigation = { navigator.popBackStack() })
    }

    ) { paddingValues ->
        ListProduct(productViewModel, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ListProduct(productViewModel: ProductViewModel, modifier:Modifier) {
    val productList by productViewModel.getProducts.observeAsState(emptyList())

    LaunchedEffect(Unit){
        productViewModel.listProducts()
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SearchProduct(modifier = Modifier, productViewModel = productViewModel)
        }

        items(productList.toList()){
            ProductCard(product = it)
        }
    }
}

@Composable
fun ProductCard(product: ProductResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    )
    {
        TextView(text = product.id)
        TextView(text = product.code)
        TextView(text = product.name)
        TextView(text = product.mark)
        TextView(text = product.packing)
        TextView(text = product.stock)
        TextView(text = product.cost.toString())
        TextView(text = product.price.toString())
        TextView(text = product.factor.toString())
        TextView(text = product.index)
        TextView(text = product.image)
    }
}

@Composable
fun SearchProduct(modifier: Modifier, productViewModel: ProductViewModel) {
    val text: String by productViewModel.searchProduct.observeAsState(initial = "")

    TextFieldView(
        text = text,
        textValue = { productViewModel.changedSearch(it) },
        label = { TextView(text = "Search") },
        modifier = modifier.fillMaxWidth()
    )

}

@Preview
@Composable
fun PreviewProducts() {
    ProductScreen(navigator = EmptyDestinationsNavigator)
}