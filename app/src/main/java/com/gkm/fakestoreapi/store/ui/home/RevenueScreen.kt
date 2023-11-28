package com.gkm.fakestoreapi.store.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gkm.fakestoreapi.R
import com.gkm.fakestoreapi.store.ui.components.TopAppBarViewBack
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun RevenueScreen(navigator: DestinationsNavigator){
    Scaffold(topBar = {
        TopAppBarViewBack(
            text = "Ganancias",
            navigation = { navigator.popBackStack() })

    }
    ) {paddingValues ->
        RevenueTable(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun RevenueTable(modifier:Modifier){
    Column (modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = stringResource(id = R.string.revenue))
    }
}