package com.novack.art_gallery.art_overview.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.persistentListOf

internal class ArtOverviewScreenParamProvider : PreviewParameterProvider<ArtOverviewParam> {
    override val values: Sequence<ArtOverviewParam>
        get() = sequenceOf(
            ArtOverviewParam.Loading,
            ArtOverviewParam.Loaded(
                artPieces = persistentListOf()
            ),
            ArtOverviewParam.Loaded(
                artPieces = persistentListOf(
                    ArtOverviewItemParam(
                        id = "1",
                        artist = "Artist",
                        title = "Title",
                        thumbnailUrl = "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/843,/0/default.jpg",
                    ),
                    ArtOverviewItemParam(
                        id = "2",
                        artist = "A kind of long artist name",
                        title = "Hopefully a long enough title to show the overflow",
                        thumbnailUrl = "broken URL",
                    ),
                    ArtOverviewItemParam(
                        id = "3",
                        artist = "Artist 3",
                        title = "Title 3",
                        thumbnailUrl = "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/843,/0/default.jpg",
                    ),
                )
            ),
        )
}