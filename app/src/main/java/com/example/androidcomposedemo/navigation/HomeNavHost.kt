package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidcomposedemo.screen.InstagramScreen
import com.example.observatory.currentWeatherInfo.screen.CurrentWeatherInfoScreen
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import com.rc.base.common.RcAppState
import com.rc.base.navigation.FeatRouting
import com.rc.facebook.ui.screen.navigation.facebookSession


@Composable
fun HomeNavHost(appState: RcAppState) {
    NavHost(
        navController = appState.homeNavHostController,
        startDestination = FeatRouting.FacebookSection.FacebookGraph
    ) {
        facebookSession(appState)

        composable<FeatRouting.Observatory> {
            CurrentWeatherInfoScreen()
        }
    }
}
