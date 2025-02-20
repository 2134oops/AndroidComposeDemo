package com.rc.base.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rc.base.navigation.MainRouting


@Composable
fun rememberRcAppState(
    mainNavHostController: NavHostController = rememberNavController(),
    homeNavHostController: NavHostController = rememberNavController()
): RcAppState {
    return remember {
        RcAppState(
            mainNavHostController = mainNavHostController,
            homeNavHostController = homeNavHostController
        )
    }
}

class RcAppState(
    val mainNavHostController: NavHostController,
    val homeNavHostController: NavHostController
) {


    @Composable
    fun isShowBottomBar(): Boolean {
        return mainNavHostController.currentBackStackEntryAsState().value?.destination?.hasRoute(
            route = MainRouting.Home::class
        )
            ?: false
    }


}