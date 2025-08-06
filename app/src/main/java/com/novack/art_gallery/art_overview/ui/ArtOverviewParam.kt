package com.novack.art_gallery.art_overview.ui

import kotlinx.collections.immutable.ImmutableList


sealed interface ArtOverviewParam {

    data object Loading : ArtOverviewParam

    data object Error : ArtOverviewParam

    data class Loaded(
        val artPieces: ImmutableList<ArtOverviewItemParam>,
    ) : ArtOverviewParam
}

data class ArtOverviewItemParam(
    val id: String,
    val artist: String?,
    val title: String?,
    val thumbnailUrl: String,
)