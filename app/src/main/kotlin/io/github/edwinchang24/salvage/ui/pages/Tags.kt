package io.github.edwinchang24.salvage.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import io.github.edwinchang24.salvage.ui.MainNavGraph
import io.github.edwinchang24.salvage.ui.components.TagCard

@MainNavGraph
@Destination
@Composable
fun TagsPage(viewModel: TagsViewModel = hiltViewModel()) {
    when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is TagsPageUiState.Loading ->
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        is TagsPageUiState.Success -> {
            if (uiState.tags.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                ) {
                    items(uiState.tags) { tag ->
                        TagCard(tag = tag, modifier = Modifier.fillMaxWidth())
                    }
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                ) {
                    Text(
                        text = "No tags have been added yet! Tap on the bottom right button to add one.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
