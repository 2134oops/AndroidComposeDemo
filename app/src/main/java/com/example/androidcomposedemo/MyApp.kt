package com.example.androidcomposedemo

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidcomposedemo.navigation.HomeNavHost
import com.rc.base.navigation.MainRouting
import com.example.androidcomposedemo.navigation.RcNavHost
import com.rc.base.common.RcAppState
import com.rc.base.theme.AndroidComposeDemoTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyApp(appState: RcAppState) {
    val currentBackStackEntry by appState.mainNavHostController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    MainRouting.Home::class.java.name
    Log.d(
        "MyApp",
        "currentDestination: ${currentDestination?.route}   |||  ${
            currentDestination?.hasRoute(route = MainRouting.Home::class)
        } |||| ${MainRouting.Home::class.java.name}"
    )
    AndroidComposeDemoTheme {
        RcNavHost(
            appState
        )
    }
}


