package com.gkm.fakestoreapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkm.fakestoreapi.store.ui.LoginViewModel
import com.gkm.fakestoreapi.store.ui.NavGraph
import com.gkm.fakestoreapi.store.ui.NavGraphs
import com.gkm.fakestoreapi.store.ui.StoreScreen
import com.gkm.fakestoreapi.store.ui.StoreViewModel
import com.gkm.fakestoreapi.store.ui.destinations.LoginScreenDestination
import com.gkm.fakestoreapi.ui.theme.FakeStoreApiTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val storeViewModel:StoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApiTheme {
                // A surface container using the 'background' color from the theme
                DestinationsNavHost(NavGraphs.root)
                //StoreScreen(storeViewModel = storeViewModel)
            }
        }
    }
}