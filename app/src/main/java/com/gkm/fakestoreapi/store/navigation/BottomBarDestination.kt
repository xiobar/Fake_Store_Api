package com.gkm.fakestoreapi.store.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector
import com.gkm.fakestoreapi.R
import com.gkm.fakestoreapi.store.destinations.HomeScreenDestination
import com.gkm.fakestoreapi.store.destinations.SettingsScreenDestination
import com.gkm.fakestoreapi.store.destinations.StoreScreenDestination
import com.gkm.fakestoreapi.store.destinations.UserScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label:Int
){
    UserScreen(UserScreenDestination, Icons.Default.VerifiedUser, R.string.user),
    StoreScreen(StoreScreenDestination, Icons.Default.Store, R.string.store),
    SettingsScreen(SettingsScreenDestination, Icons.Default.Settings, R.string.setting),
    HomeScreen(HomeScreenDestination, Icons.Default.Home, R.string.home)
}