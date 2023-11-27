package io.github.edwinchang24.salvage.ui.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle
import io.github.edwinchang24.salvage.ui.appDestination
import io.github.edwinchang24.salvage.ui.destinations.Destination

open class Transitions(
    private val destination: Destination,
    private val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = { null },
    private val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = { null }
) : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
        if (targetState.appDestination() == destination) enter() else null

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
        if (initialState.appDestination() == destination) exit() else null

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() =
        if (targetState.appDestination() == destination) enter() else null

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() =
        if (initialState.appDestination() == destination) exit() else null
}
