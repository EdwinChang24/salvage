package io.github.edwinchang24.salvage.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.data.BookmarkRepository
import io.github.edwinchang24.salvage.model.Bookmark
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavedViewModel
    @Inject
    constructor(bookmarkRepository: BookmarkRepository) : ViewModel() {
        val uiState =
            bookmarkRepository.getBookmarks().map { SavedUiState.Success(it) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SavedUiState.Loading
            )
    }

sealed interface SavedUiState {
    data object Loading : SavedUiState

    data class Success(val bookmarks: List<Bookmark>) : SavedUiState
}
