@file:OptIn(ExperimentalAnimationApi::class)

package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.foodie.ui.presentation.NavGraphs
import com.example.foodie.ui.theme.FoodieTheme
import com.example.foodie.ui.transitions.DefaultTransitionSet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieTheme {
                val engine = rememberAnimatedNavHostEngine(
                    navHostContentAlignment = Alignment.TopCenter,
                    rootDefaultAnimations = DefaultTransitionSet.DEFAULT_SCREEN_TRANSITIONS
                )
                val navController: NavHostController = engine.rememberNavController()

                DestinationsNavHost(
                    navController = navController,
                    navGraph = NavGraphs.root,
                    engine = engine
                )
            }
        }
    }
}
