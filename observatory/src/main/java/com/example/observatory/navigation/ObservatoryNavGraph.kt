package com.example.observatory.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.observatory.currentWeatherInfo.screen.CurrentWeatherInfoScreen
import com.example.observatory.currentWeatherInfo.viewModel.CurrentWeatherInfoViewModel
import com.rc.base.common.RcAppState
import com.rc.base.navigation.FeatRouting
import org.koin.androidx.compose.koinViewModel


fun NavHostController.navigateToObservatory(navOptions: NavOptions) {
    navigate(FeatRouting.ObservatorySection.ObservatoryGraph, navOptions)
}

fun NavGraphBuilder.observatorySession(appState: RcAppState) {
    navigation<FeatRouting.ObservatorySection.ObservatoryGraph>(
        startDestination = FeatRouting.ObservatorySection.CurrentWeatherInfo
    ) {
        composable<FeatRouting.ObservatorySection.CurrentWeatherInfo> {
            val viewModel = koinViewModel<CurrentWeatherInfoViewModel>()
            CurrentWeatherInfoScreen(appState = appState, viewModel = viewModel)
        }
    }
}
