package io.github.edwinchang24.salvage.model

import kotlinx.datetime.Instant

data class Bookmark(
    val bookmarkId: String,
    val name: String?,
    val url: String,
    val description: String?,
    val timeAdded: Instant,
    val timePublished: Instant?
)
