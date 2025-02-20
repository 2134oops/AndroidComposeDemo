package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rc.base.common.RcAppState
import com.rc.facebook.ui.screen.FacebookDetailScreen
import com.rc.facebook.ui.screen.facebookList.FacebookScreen
import com.example.androidcomposedemo.screen.InstagramScreen
import com.rc.base.navigation.FeatRouting
import com.rc.facebook.ui.screen.facebookList.viewModel.FacebookListViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeNavHost(appState: RcAppState) {
    NavHost(
        navController = appState.homeNavHostController,
        startDestination = FeatRouting.FacebookSection.FacebookGraph
    ) {
        facebookSession(appState)

        composable<FeatRouting.Instagram> {
            InstagramScreen()
        }
    }
}

fun NavHostController.navigateToFacebook(navOptions: NavOptions) {
    navigate(FeatRouting.FacebookSection.Facebook, navOptions)
}

fun NavGraphBuilder.facebookSession(appState: RcAppState) {
    navigation<FeatRouting.FacebookSection.FacebookGraph>(
        startDestination = FeatRouting.FacebookSection.Facebook,
    ) {
        composable<FeatRouting.FacebookSection.Facebook> {
            val viewModel: FacebookListViewModel = koinViewModel()
            FacebookScreen(appState = appState, viewModel = viewModel)
        }
        composable<FeatRouting.FacebookSection.FacebookDetail> {
            FacebookDetailScreen()
        }
    }

}