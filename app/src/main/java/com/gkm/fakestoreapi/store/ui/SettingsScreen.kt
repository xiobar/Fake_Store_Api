package com.gkm.fakestoreapi.store.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen() {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Setting")
    }
}