package com.rc.base.navigation

import kotlinx.serialization.Serializable

sealed class MainRouting {
    @Serializable
    data object Splash : MainRouting()

    @Serializable
    data object Home : MainRouting()

}

@Serializable
sealed class FeatRouting {

    @Serializable
    sealed class FacebookSection : FeatRouting() {
        @Serializable
        data object FacebookGraph : FacebookSection()

        @Serializable
        data object Facebook : FacebookSection()
        @Serializable
        data object FacebookDetail : FacebookSection()

    }


    @Serializable
    data object Instagram : FeatRouting()

    @Serializable
    data object Observatory : FeatRouting()
}