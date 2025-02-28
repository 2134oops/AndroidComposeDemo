package com.rc.base.navigation

import kotlinx.serialization.Serializable

sealed class MainRouting {
    @Serializable
    data object Splash : MainRouting()

    @Serializable
    data object Home : MainRouting()

    sealed class TestingRouting {
        @Serializable
        data object TestingList : TestingRouting()
        @Serializable
        data object TestingDetail : TestingRouting()
    }

    @Serializable
    sealed class FacebookSection : MainRouting() {
        @Serializable
        data object FacebookGraph : FacebookSection()

        @Serializable
        data object Facebook : FacebookSection()
        @Serializable
        data object FacebookDetail : FacebookSection()

    }


    @Serializable
    data object Instagram : MainRouting()

    @Serializable
    data object Observatory : MainRouting()

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