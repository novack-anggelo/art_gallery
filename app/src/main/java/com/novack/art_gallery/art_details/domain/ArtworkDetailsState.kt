package com.novack.art_gallery.art_details.domain

import com.novack.art_gallery.art_details.domain.models.ArtworkDetails

sealed interface ArtworkDetailsState {
    data object Loading : ArtworkDetailsState

    data object Error : ArtworkDetailsState

    data class Loaded(
        val  artworkDetails: ArtworkDetails,
    ) : ArtworkDetailsState
}