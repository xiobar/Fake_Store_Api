package com.gkm.fakestoreapi.store.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UserScreen (navigator: DestinationsNavigator){
    Column {
        Button(onClick = { navigator.popBackStack()}) {
            Text(text = "Users")
        }
    }
}