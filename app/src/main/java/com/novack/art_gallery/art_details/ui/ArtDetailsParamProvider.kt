package com.novack.art_gallery.art_details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ArtDetailsParamProvider : PreviewParameterProvider<ArtDetailsParam> {
    override val values: Sequence<ArtDetailsParam> = sequenceOf(
        ArtDetailsParam.Loading,
        ArtDetailsParam.Error,
        ArtDetailsParam.Loaded(
            artDetails = ArtDetailsInfoParam(
                id = "test-id-1",
                title = "The Starry Night",
                imageUrl = "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/843,/0/default.jpg",
                description = "The Starry Night is an oil-on-canvas painting by the Dutch Post-Impressionist painter Vincent van Gogh. Painted in June 1889, it depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence, just before sunrise, with the inclusion of an imaginary village.",
                date = "1889",
                artist = "Vincent van Gogh",
            )
        )
    )
}
