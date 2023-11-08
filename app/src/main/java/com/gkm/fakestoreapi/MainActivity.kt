package com.gkm.fakestoreapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gkm.fakestoreapi.store.ui.StoreScreen
import com.gkm.fakestoreapi.store.ui.StoreViewModel
import com.gkm.fakestoreapi.ui.theme.FakeStoreApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val storeViewModel:StoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApiTheme {
                // A surface container using the 'background' color from the theme
                StoreScreen(storeViewModel = storeViewModel)
            }
        }
    }
}