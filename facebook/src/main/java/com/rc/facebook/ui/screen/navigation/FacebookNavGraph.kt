package com.rc.facebook.ui.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rc.base.common.RcAppState
import com.rc.base.navigation.FeatRouting
import com.rc.facebook.ui.screen.FacebookDetailScreen
import com.rc.facebook.ui.screen.facebookList.FacebookScreen
import com.rc.facebook.ui.screen.facebookList.viewModel.FacebookListViewModel
import org.koin.androidx.compose.koinViewModel


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