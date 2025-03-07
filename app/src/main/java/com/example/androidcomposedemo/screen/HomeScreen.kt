package com.example.androidcomposedemo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.rc.base.common.RcAppState
import com.rc.base.navigation.FeatRouting
import com.example.androidcomposedemo.navigation.HomeNavHost
import com.example.observatory.navigation.navigateToObservatory

import com.rc.base.util.getCustomColor
import com.rc.facebook.ui.screen.navigation.navigateToFacebook


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(appState: RcAppState) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabScreenList = listOf(
        FeatRouting.FacebookSection.Facebook,
        FeatRouting.ObservatorySection.CurrentWeatherInfo
    )

    val currentDestination =
        appState.homeNavHostController.currentBackStackEntryAsState().value?.destination

    val isBottomNavigationScreen =
        tabScreenList.any { currentDestination?.hasRoute(it::class) == true }

    val bottomNavigationBarBackgroundColor =
        when {
            currentDestination?.hasRoute(FeatRouting.FacebookSection.Facebook::class) == true -> {
                getCustomColor().facebookDarkGrey
            }

            currentDestination?.hasRoute(FeatRouting.ObservatorySection.CurrentWeatherInfo::class) == true -> {
                getCustomColor().observatoryGrey
            }

            else -> {
                Color.White

            }
        }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            if (isBottomNavigationScreen)
                BottomNavigationBar(
                    selectedIndex = selectedTab,
                    backgroundColor = bottomNavigationBarBackgroundColor
                ) {
                    selectedTab = it
                    when (it) {
                        0 -> appState.homeNavHostController.navigateToFacebook(navOptions {
                            popUpTo(
                                FeatRouting.FacebookSection.Facebook
                            )
                        })

                        1 -> appState.homeNavHostController.navigateToObservatory(navOptions {
                        })
                    }
                }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            HomeNavHost(appState = appState)
        }
    }
}


@Composable
fun BottomNavigationBar(backgroundColor: Color, selectedIndex: Int, tabItemClick: (Int) -> Unit) {
    NavigationBar(
        containerColor = backgroundColor
    ) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text(text = "Facebook") },
            onClick = {
                tabItemClick(0)
            }
        )
//        NavigationBarItem(
//            selected = false,
//            icon = { Text(text = "Instagram") },
//            label = { Text(text = "Instagram") },
//            onClick = {
//                tabItemClick(1)
//            }
//        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            icon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
            label = { Text(text = "Observatory") },
            onClick = {
                tabItemClick(1)
            }
        )
    }
}