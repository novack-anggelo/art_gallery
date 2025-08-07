package com.novack.art_gallery.art_overview.domain

import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview

sealed interface ArtworksOverviewState {
    data object Loading : ArtworksOverviewState

    data object Error : ArtworksOverviewState

    data class Loaded(
        val artworks: List<ArtworkOverview>
    ) : ArtworksOverviewState
}