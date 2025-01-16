package com.example.androidcomposedemo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.androidcomposedemo.navigation.MainRouting
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(null) {
        delay(3000)
        navHostController.navigate(MainRouting.Home)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Splash Screen")
    }
}

