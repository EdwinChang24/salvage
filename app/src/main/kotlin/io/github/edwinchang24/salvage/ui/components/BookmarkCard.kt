package io.github.edwinchang24.salvage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.edwinchang24.salvage.model.Bookmark
import io.github.edwinchang24.salvage.ui.theme.SalvageTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkCard(bookmark: Bookmark, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(onClick = onClick, modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(24.dp)) {
            bookmark.name?.let { Text(it, style = MaterialTheme.typography.titleLarge) }
            Text(bookmark.url, style = MaterialTheme.typography.titleSmall)
            bookmark.description?.let { Text(it, style = MaterialTheme.typography.bodyLarge) }
            bookmark.timePublished?.let { Text(it.toString(), style = MaterialTheme.typography.bodySmall) }
        }
    }
}

@Preview
@Composable
private fun BookmarkCardPreview() {
    SalvageTheme {
        Surface {
            BookmarkCard(
                bookmark =
                    Bookmark(
                        bookmarkId = "0",
                        name = "OpenAI's board has fired Sam Altman",
                        url = "https://openai.com/blog/openai-announces-leadership-transition",
                        description =
                            """
                            Chief technology officer Mira Murati appointed interim CEO to lead OpenAI; Sam Altman departs the company.
                            Search process underway to identify permanent successor.
                            """.trimIndent(),
                        timeAdded = Clock.System.now(),
                        timePublished = Instant.parse("2023-11-17T20:28:50Z")
                    ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
