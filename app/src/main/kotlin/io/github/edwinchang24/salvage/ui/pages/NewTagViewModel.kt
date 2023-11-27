package io.github.edwinchang24.salvage.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.edwinchang24.salvage.data.TagRepository
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewTagViewModel
    @Inject
    constructor(private val tagRepository: TagRepository) : ViewModel() {
        fun createTag(name: String, color: Int, description: String?) {
            viewModelScope.launch(Dispatchers.IO) {
                tagRepository.addTag(
                    Tag(
                        tagId = UUID.randomUUID().toString(),
                        name = name,
                        color = color,
                        description = description
                    )
                )
            }
        }
    }
