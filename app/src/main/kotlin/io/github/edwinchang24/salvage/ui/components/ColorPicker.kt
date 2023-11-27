package io.github.edwinchang24.salvage.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.ui.theme.SalvageTheme
import io.github.edwinchang24.salvage.util.mixColors

val presetColors =
    listOf(
        0xFF0000,
        0xFFA500,
        0xFFFF00,
        0x00FF00,
        0x0000FF,
        0xFF00FF
    )

@Composable
fun ColorPicker(
    colorInt: Int,
    onColorIntChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    rowPadding: PaddingValues = PaddingValues()
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        val state = rememberLazyListState()
        LazyRow(
            state = state,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = rowPadding
        ) {
            items(presetColors) { presetColor ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(mixColors(listOf(Color(presetColor), MaterialTheme.colorScheme.surface)))
                            .clickable { onColorIntChange(presetColor) }
                ) {
                    if (colorInt == presetColor) Icon(Icons.Default.Check, contentDescription = null)
                }
            }
        }
        if (colorInt !in presetColors) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Selected:", style = MaterialTheme.typography.bodySmall)
                Box(
                    modifier =
                        Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(mixColors(listOf(Color(colorInt), MaterialTheme.colorScheme.surface)))
                )
            }
        }
    }
}

@Preview
@Composable
private fun ColorPickerPreview() {
    SalvageTheme {
        Surface {
            var colorInt by rememberSaveable { mutableIntStateOf(0x000000) }
            ColorPicker(colorInt = colorInt, onColorIntChange = { colorInt = it }, modifier = Modifier.padding(16.dp))
        }
    }
}
