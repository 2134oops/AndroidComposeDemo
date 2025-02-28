package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rc.base.common.RcAppState
import com.example.androidcomposedemo.screen.HomeScreen
import com.example.androidcomposedemo.screen.SplashScreen
import com.rc.base.navigation.MainRouting


@Composable
fun RcNavHost(appState: RcAppState) {
    NavHost(
        navController = appState.mainNavHostController,
        startDestination = MainRouting.Splash
    ) {
        composable<MainRouting.Splash> {
            SplashScreen(appState)
        }

        composable<MainRouting.Home>(enterTransition = enterTransition) {

            HomeScreen(appState)
        }
    }
}
