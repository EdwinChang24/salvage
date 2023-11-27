package io.github.edwinchang24.salvage.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import io.github.edwinchang24.salvage.ui.MainNavGraph
import io.github.edwinchang24.salvage.ui.components.BookmarkCard
import kotlinx.coroutines.launch

@MainNavGraph(start = true)
@Destination
@Composable
fun SavedPage(viewModel: SavedViewModel = hiltViewModel()) {
    when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is SavedUiState.Loading ->
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        is SavedUiState.Success -> {
            if (uiState.bookmarks.isNotEmpty()) {
                val state = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()
                LazyColumn(
                    state = state,
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                ) {
                    items(uiState.bookmarks) { bookmark ->
                        BookmarkCard(bookmark = bookmark, modifier = Modifier.fillMaxWidth())
                    }
                    item {
                        OutlinedButton(
                            onClick = { coroutineScope.launch { state.animateScrollToItem(0) } },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Back to top")
                        }
                    }
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                ) {
                    Text(
                        text = "No bookmarks have been added yet! Tap on the bottom right button to add one.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
