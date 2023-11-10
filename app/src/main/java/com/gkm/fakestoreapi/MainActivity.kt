package com.gkm.fakestoreapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.gkm.fakestoreapi.store.destinations.Destination
import com.gkm.fakestoreapi.store.navigation.BuildNavGraph
import com.gkm.fakestoreapi.store.ui.LoginScreen
import com.gkm.fakestoreapi.store.ui.StoreViewModel
import com.gkm.fakestoreapi.ui.theme.FakeStoreApiTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val storeViewModel:StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApiTheme {
                // A surface container using the 'background' color from the theme
                LoginScreen(navigator = )
                //StoreScreen(storeViewModel = storeViewModel)
            }
        }
    }
}