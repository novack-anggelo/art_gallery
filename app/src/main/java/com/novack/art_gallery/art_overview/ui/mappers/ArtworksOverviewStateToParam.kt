package com.novack.art_gallery.art_overview.ui.mappers

import com.novack.art_gallery.art_overview.domain.ArtworksOverviewState
import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview
import com.novack.art_gallery.art_overview.ui.ArtOverviewItemParam
import com.novack.art_gallery.art_overview.ui.ArtOverviewParam
import kotlinx.collections.immutable.toImmutableList

internal fun ArtworksOverviewState.toParam() = when(this) {
    ArtworksOverviewState.Error -> ArtOverviewParam.Error

    ArtworksOverviewState.Loading -> ArtOverviewParam.Loading

    is ArtworksOverviewState.Loaded -> ArtOverviewParam.Loaded(
        artPieces = this.artworks.map { it.toParam() }.toImmutableList()
    )
}

private fun ArtworkOverview.toParam() = ArtOverviewItemParam(
    id = id,
    artist = artist.orEmpty(),
    title = title,
    thumbnailUrl = thumbnailUrl,
)