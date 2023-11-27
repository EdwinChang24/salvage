package io.github.edwinchang24.salvage.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.data.TagRepository
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TagsViewModel
    @Inject
    constructor(tagRepository: TagRepository) : ViewModel() {
        val uiState =
            tagRepository.getTags().map { TagsPageUiState.Success(it) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = TagsPageUiState.Loading
            )
    }

sealed interface TagsPageUiState {
    data object Loading : TagsPageUiState

    data class Success(val tags: List<Tag>) : TagsPageUiState
}
