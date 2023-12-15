@file:OptIn(ExperimentalAnimationApi::class)

package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.example.foodie.ui.presentation.NavGraphs
import com.example.foodie.ui.presentation.appCurrentDestinationAsState
import com.example.foodie.ui.presentation.destinations.RecipeDetailsPageDestination
import com.example.foodie.ui.theme.FoodieTheme
import com.example.foodie.ui.transitions.DefaultTransitionSet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.spec.Route
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FoodieTheme {
                val engine = rememberAnimatedNavHostEngine(
                    navHostContentAlignment = Alignment.TopCenter,
                    rootDefaultAnimations = DefaultTransitionSet.DEFAULT_SCREEN_TRANSITIONS
                )
                val navController: NavHostController = engine.rememberNavController()
                val currentDestination =
                    navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startRoute
                SystemBarsColor(destination = currentDestination)
                Column(Modifier.fillMaxSize()) {
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.root,
                        engine = engine
                    )
                }
            }
        }
    }

}

@Composable
private fun SystemBarsColor(destination: Route) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons =
        when (destination) {
            RecipeDetailsPageDestination -> false
            else -> true
        }
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = useDarkIcons
    )
    if (systemUiController.isNavigationBarVisible) {
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
            navigationBarContrastEnforced = false
        )
    }
}
