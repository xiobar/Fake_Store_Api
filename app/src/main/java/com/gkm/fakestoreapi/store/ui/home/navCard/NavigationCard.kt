package com.gkm.fakestoreapi.store.ui.home.navCard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gkm.fakestoreapi.R
import com.gkm.fakestoreapi.store.destinations.ClientScreenDestination
import com.gkm.fakestoreapi.store.destinations.ProductScreenDestination
import com.gkm.fakestoreapi.store.destinations.RevenueScreenDestination
import com.gkm.fakestoreapi.store.destinations.StatisticsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class NavigationCard(
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
) {
    ClientScreen(ClientScreenDestination, R.drawable.client_icon, R.string.client),
    ProductScreen(ProductScreenDestination, R.drawable.product_icon, R.string.product),
    RevenueScreen(RevenueScreenDestination, R.drawable.revenue_icon, R.string.revenue),
    StatisticsScreen(StatisticsScreenDestination, R.drawable.statistics_icon, R.string.statistics),
}