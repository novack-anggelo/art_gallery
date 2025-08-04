package com.novack.art_gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.novack.art_gallery.common.ui.navigation.AppScreens
import com.novack.art_gallery.ui.theme.Art_galleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Art_galleryTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppScreens.ArtOverview.route,
                ) {
                    composable(AppScreens.ArtOverview.route) {
                        Text("Art Overview Screen")
                    }
                    composable(
                        route = AppScreens.ArtDetails.route,
                        arguments = listOf(navArgument("artworkId") {
                            type = NavType.StringType
                            nullable = true
                        })
                    ) { backStackEntry ->
                        val artworkId = backStackEntry.arguments?.getString("artworkId")
                        Text("Art Details Screen. ID: ${artworkId ?: "None"}")
                    }
                }
            }
        }
    }
}
