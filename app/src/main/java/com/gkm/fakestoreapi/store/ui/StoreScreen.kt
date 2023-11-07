package com.gkm.fakestoreapi.store.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkm.fakestoreapi.store.data.StoreResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(modifier: Modifier = Modifier, storeViewModel: StoreViewModel) {

    val storeList = storeViewModel.getProducts.collectAsState(emptyList())

    LaunchedEffect(Unit){
        storeViewModel.listProducts()
    }

    Scaffold(modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Store") })
        }) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {
                Text(text = "Listado de venta")
            }
            items(storeList.value) { store ->
                StoreCard(store = store)
            }
        }
    }
}

@Composable
fun StoreCard(store:StoreResponse){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )) {
        Text(text = store.codigo.toString())
        Text(text = store.titulo)
        Text(text = store.descripcion)
        Text(text = store.precio.toString())
        Text(text = store.categoria)
    }
}

@Preview
@Composable
fun StorePreview() {
    StoreScreen(storeViewModel = StoreViewModel())
}