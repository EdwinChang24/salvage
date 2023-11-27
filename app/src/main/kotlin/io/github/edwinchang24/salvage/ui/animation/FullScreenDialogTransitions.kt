package io.github.edwinchang24.salvage.ui.animation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import io.github.edwinchang24.salvage.ui.destinations.Destination

open class FullScreenDialogTransitions(destination: Destination) :
    Transitions(
        destination = destination,
        enter = { slideInVertically { fullHeight -> fullHeight } },
        exit = { slideOutVertically { fullHeight -> fullHeight } }
    )
