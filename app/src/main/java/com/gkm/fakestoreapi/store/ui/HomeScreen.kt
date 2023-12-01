package com.gkm.fakestoreapi.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkm.fakestoreapi.R
import com.gkm.fakestoreapi.store.ui.components.OptionsDesigns
import com.gkm.fakestoreapi.store.ui.home.ProductViewModel
import com.gkm.fakestoreapi.store.ui.home.navCard.NavigationCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                HeaderHome(modifier = Modifier.weight(0.6f), loginViewModel, productViewModel)
                FooterHome(modifier = Modifier.weight(1f), navigator)
            }
        }
    }
}

@Composable
fun FooterHome(modifier: Modifier, navController: DestinationsNavigator) {

    val navigateHomeItems = listOf(
        NavigationCard.ClientScreen,
        NavigationCard.ProductScreen
    )

    val navigateHomeItem = listOf(
        NavigationCard.RevenueScreen,
        NavigationCard.StatisticsScreen
    )

    Box(
        modifier
            .padding(10.dp)
    ) {
        Column {
            Box(
                Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navigateHomeItems.forEach {
                        OptionsDesigns(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(it.direction)
                                },
                            image = it.icon,
                            name = it.label
                        )
                    }
                }
            }

            Box(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navigateHomeItem.forEach {
                        OptionsDesigns(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(it.direction)
                                },
                            image = it.icon,
                            name = it.label
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderHome(modifier: Modifier,
               loginViewModel: LoginViewModel,
               productViewModel: ProductViewModel) {
    val user by loginViewModel.readCredential.collectAsState()
    val token by loginViewModel.readAuthorizade.collectAsState()
    productViewModel.setToken(token.token)
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(60.dp).copy(
            topStart = ZeroCornerSize,
            topEnd = ZeroCornerSize,
            bottomEnd = ZeroCornerSize
        ),
        shadowElevation = 10.dp,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "Hola ${user.name} !",
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.surface,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.avatar_image),
                        contentDescription = "user",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopEnd,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(60.dp)
                    )
                }
            }
            Box(
                Modifier
                    .weight(0.8f)
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = "Ventas del dia",
                        color = MaterialTheme.colorScheme.surface
                    )
                    Text(
                        text = "$1260.40",
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    HomeScreen(navigator = EmptyDestinationsNavigator)
}