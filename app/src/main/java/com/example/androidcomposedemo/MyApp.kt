package com.example.androidcomposedemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.androidcomposedemo.navigation.RcNavHost
import com.example.androidcomposedemo.ui.theme.AndroidComposeDemoTheme


@Composable
fun MyApp() {
    val navHostController = rememberNavController()
    val featureNavHostController = rememberNavController()
    AndroidComposeDemoTheme {
        RcNavHost(
            navHostController = navHostController,
            featureNavHostController = featureNavHostController
        )
    }
}