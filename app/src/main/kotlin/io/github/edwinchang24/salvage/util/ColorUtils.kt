package io.github.edwinchang24.salvage.util

import androidx.compose.ui.graphics.Color

fun mixColors(colors: Collection<Color>) =
    colors
        .map {
            Triple(
                first = it.red,
                second = it.green,
                third = it.blue
            )
        }
        .reduce { color1, color2 ->
            Triple(
                first = color1.first + color2.first,
                second = color1.second + color2.second,
                third = color1.third + color2.third
            )
        }
        .let {
            Color(
                red = it.first / colors.size,
                green = it.second / colors.size,
                blue = it.third / colors.size
            )
        }
