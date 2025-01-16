package com.example.androidcomposedemo.navigation

import kotlinx.serialization.Serializable

sealed class MainRouting {
    @Serializable data object Splash : MainRouting()

    @Serializable data object Home : MainRouting()

}

sealed class FeatRouting {
    @Serializable data object Facebook : FeatRouting()

    @Serializable data object Instagram : FeatRouting()

    @Serializable data object Observatory : FeatRouting()
}