package com.novack.art_gallery.common.ui.navigation

import androidx.navigation.NavArgument

sealed class AppScreens(val route: String) {
    data object ArtOverview : AppScreens("art_overview_screen")

    data object ArtDetails : AppScreens("art_details_screen?artworkId={artworkId}") {
        fun createRoute(artworkId: String): String {
            return "art_details_screen?artworkId=$artworkId"
        }
    }
}