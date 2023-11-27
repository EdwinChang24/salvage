package io.github.edwinchang24.salvage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.model.Tag
import io.github.edwinchang24.salvage.ui.theme.SalvageTheme
import io.github.edwinchang24.salvage.util.mixColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagCard(tag: Tag, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        colors =
            CardDefaults.cardColors(
                containerColor = mixColors(listOf(Color(tag.color), MaterialTheme.colorScheme.surface))
            ),
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(24.dp)) {
            Text(tag.name, style = MaterialTheme.typography.titleLarge)
            tag.description?.let { Text(it, style = MaterialTheme.typography.bodyLarge) }
        }
    }
}

@Preview
@Composable
private fun TagCardPreview() {
    SalvageTheme {
        Surface {
            TagCard(
                tag =
                    Tag(
                        tagId = "0",
                        name = "Tech",
                        color = 0xFFED00,
                        description = "Blogs, articles, and other pages related to modern technology"
                    ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
