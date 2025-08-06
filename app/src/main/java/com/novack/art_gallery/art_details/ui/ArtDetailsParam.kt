package com.novack.art_gallery.art_details.ui

interface ArtDetailsParam {
    data object Loading : ArtDetailsParam

    data object Error : ArtDetailsParam

    data class Loaded(
        val artDetails: ArtDetailsInfoParam,
    ) : ArtDetailsParam
}

data class ArtDetailsInfoParam (
    val id: String,
    val title: String?,
    val description: String?,
    val date: String?,
    val artist: String?,
    val imageUrl: String,
)