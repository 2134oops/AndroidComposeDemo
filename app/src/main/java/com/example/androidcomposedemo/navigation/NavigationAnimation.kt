package com.example.androidcomposedemo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavBackStackEntry


val enterTransition : AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition= {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(1000)
    )
}