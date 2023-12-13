package com.example.foodie.ui.transitions

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.ramcosta.composedestinations.animations.defaults.DestinationEnterTransition
import com.ramcosta.composedestinations.animations.defaults.DestinationExitTransition
import com.ramcosta.composedestinations.animations.defaults.NavGraphDefaultAnimationParams
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

const val ANIMATION_DURATION = 150
@ExperimentalAnimationApi
class DefaultTransitionSet(
    override val enterTransition: DestinationEnterTransition? = null,
    override val exitTransition: DestinationExitTransition? = null,
    override val popEnterTransition: DestinationEnterTransition? = enterTransition,
    override val popExitTransition: DestinationExitTransition? = exitTransition,
) : NavGraphDefaultAnimationParams {
    companion object {
        //this is the default transition that will be used if not other is provided
        val DEFAULT_SCREEN_TRANSITIONS by lazy {
            RootNavGraphDefaultAnimations(
                enterTransition = {
                    fadeIn(animationSpec = tween(ANIMATION_DURATION * 2))
                    slideInHorizontally(
                        animationSpec = tween(ANIMATION_DURATION),
                        initialOffsetX = { it })
                },
                popEnterTransition = {
                    slideInHorizontally(
                        animationSpec = tween(ANIMATION_DURATION),
                        initialOffsetX = { -it })
                },
                exitTransition = {
                    slideOutHorizontally(
                        animationSpec = tween(ANIMATION_DURATION),
                        targetOffsetX = { -it })
                },
                popExitTransition = {
                    slideOutHorizontally(
                        animationSpec = tween(ANIMATION_DURATION),
                        targetOffsetX = { it })
                }
            )
        }
    }
}
