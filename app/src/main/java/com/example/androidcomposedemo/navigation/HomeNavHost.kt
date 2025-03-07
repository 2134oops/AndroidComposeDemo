package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.observatory.navigation.observatorySession
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

        observatorySession(appState)
    }
}
