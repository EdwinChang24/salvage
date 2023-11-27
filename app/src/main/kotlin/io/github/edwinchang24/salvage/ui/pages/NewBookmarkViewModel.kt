package io.github.edwinchang24.salvage.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.data.BookmarkRepository
import io.github.edwinchang24.salvage.model.Bookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewBookmarkViewModel
    @Inject
    constructor(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
        fun createBookmark(name: String?, url: String, description: String?) {
            viewModelScope.launch(Dispatchers.IO) {
                bookmarkRepository.addBookmark(
                    Bookmark(
                        bookmarkId = UUID.randomUUID().toString(),
                        name = name,
                        url = url,
                        description = description,
                        timeAdded = Clock.System.now(),
                        timePublished = null
                    )
                )
            }
        }
    }
