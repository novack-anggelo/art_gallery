package com.novack.art_gallery.art_details.domain.models

data class ArtworkDetails(
    val id: String,
    val title: String?,
    val description: String?,
    val date: String?,
    val artist: String?,
    val imageUrl: String,
)
