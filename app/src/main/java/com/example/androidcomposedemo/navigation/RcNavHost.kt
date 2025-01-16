package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidcomposedemo.screen.FacebookScreen
import com.example.androidcomposedemo.screen.HomeScreen
import com.example.androidcomposedemo.screen.SplashScreen


@Composable
fun RcNavHost(navHostController: NavHostController, featureNavHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = MainRouting.Splash
    ) {
        composable<MainRouting.Splash> {
            SplashScreen(navHostController)
        }

        composable<MainRouting.Home> {
            HomeScreen(featureNavHostController)
        }
    }
}
