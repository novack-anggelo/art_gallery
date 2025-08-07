package com.novack.art_gallery.art_details.ui.mappers

import com.novack.art_gallery.art_details.domain.ArtworkDetailsState
import com.novack.art_gallery.art_details.ui.ArtDetailsInfoParam
import com.novack.art_gallery.art_details.ui.ArtDetailsParam

internal fun ArtworkDetailsState.toParam() = when(this) {
    ArtworkDetailsState.Error -> ArtDetailsParam.Error
    ArtworkDetailsState.Loading -> ArtDetailsParam.Loading
    is ArtworkDetailsState.Loaded -> ArtDetailsParam.Loaded(
        artDetails = ArtDetailsInfoParam(
            id = artworkDetails.id,
            artist = artworkDetails.artist,
            title = artworkDetails.title,
            imageUrl = artworkDetails.imageUrl,
            description = artworkDetails.description,
            date = artworkDetails.date,
        )
    )
}