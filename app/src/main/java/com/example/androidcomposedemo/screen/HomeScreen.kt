package com.example.androidcomposedemo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.waterfall
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidcomposedemo.navigation.FeatRouting
import com.example.androidcomposedemo.navigation.HomeNavHost

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar( ) {
                NavigationBarItem(
                    selected = true,
                    icon = { Text(text = "Facebook") },
                    label = { Text(text = "Facebook") },
                    onClick = { navHostController.navigate(FeatRouting.Facebook) }
                )
                NavigationBarItem(
                    selected = true,
                    icon = { Text(text = "Instagram") },
                    label = { Text(text = "Instagram") },
                    onClick = { navHostController.navigate(FeatRouting.Instagram) }
                )
                NavigationBarItem(
                    selected = true,
                    icon = { Text(text = "Observatory") },
                    label = { Text(text = "Observatory") },
                    onClick = { navHostController.navigate(FeatRouting.Facebook) }
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical))
                .fillMaxSize()
                .padding(it)
        ) {
            HomeNavHost(navHostController)
        }
    }
}