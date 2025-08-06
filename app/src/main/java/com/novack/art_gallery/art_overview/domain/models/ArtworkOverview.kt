package com.novack.art_gallery.art_overview.domain.models

data class ArtworkOverview(
    val id: String,
    val title: String,
    val artist: String?,
    val thumbnailUrl: String,
)
