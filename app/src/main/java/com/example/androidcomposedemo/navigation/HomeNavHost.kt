package com.example.androidcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidcomposedemo.screen.FacebookScreen
import com.example.androidcomposedemo.screen.InstagramScreen

@Composable
fun HomeNavHost(navHostController: NavHostController) {
    NavHost(
        navHostController,
        FeatRouting.Facebook
    ) {
        composable<FeatRouting.Facebook> {
            FacebookScreen()
        }
        composable<FeatRouting.Instagram> {
            InstagramScreen()
        }
    }
}