package com.gkm.fakestoreapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.gkm.fakestoreapi.store.ui.LoginViewModel
import com.gkm.fakestoreapi.ui.theme.FakeStoreApiTheme
import com.gkm.fakestoreapi.store.navigation.BuildNavGraph
import com.gkm.fakestoreapi.store.ui.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel :LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApiTheme {
                val login = loginViewModel.isLoggedIn.observeAsState(initial = false)
                // A surface container using the 'background' color from the theme
                if(login.value){
                    BuildNavGraph()
                }else{
                    LoginScreen(loginViewModel)
                }
                //StoreScreen(storeViewModel = storeViewModel)
            }
        }
    }
}