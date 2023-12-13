package com.gkm.fakestoreapi.store.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gkm.fakestoreapi.store.data.ProductResponse
import com.gkm.fakestoreapi.store.ui.components.TextFieldView
import com.gkm.fakestoreapi.store.ui.components.TextView
import com.gkm.fakestoreapi.store.ui.components.TopAppBarViewBack
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
    })
    { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            SearchProduct(modifier = Modifier, productViewModel = productViewModel)
            ListProduct(productViewModel, modifier = Modifier)

        }
    }
}

@Composable
fun ListProduct(productViewModel: ProductViewModel, modifier: Modifier) {
    val productList by productViewModel.getProducts.observeAsState(emptyList())
    val loading by productViewModel.loading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        productViewModel.listProducts()
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(productList.toList()) {
                ProductCard(product = it)
            }
        }
    }
}

@Composable
fun ProductCard(product: ProductResponse, productViewModel: ProductViewModel = hiltViewModel()) {
    productViewModel.setImage(product.image)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    )
    {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data( suspend { productViewModel.listImage() } )
                    .build(),
                contentDescription = product.name
            )

            Column {
                TextView(text = product.name)
                TextView(text = product.code)
                TextView(text = product.mark)
                TextView(text = product.packing)
                TextView(text = product.stock)
                TextView(text = product.price.toString())
                TextView(text = product.index)
            }
        }
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