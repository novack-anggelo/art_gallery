package com.novack.art_gallery.common.data.mappers

import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview
import com.novack.art_gallery.common.data.model.OverviewDataDTO
import com.novack.art_gallery.common.data.model.OverviewResponseDTO

internal const val iiifImageBaseUrl = "https://www.artic.edu/iiif/2/"
internal const val imageSize = "/full/843,/0/default.jpg"

internal fun OverviewResponseDTO.toDomain() = data.filter { it.imageId != null}.map {
    it.toDomain()
}

internal fun OverviewDataDTO.toDomain() = ArtworkOverview(
    id = id.toString(),
    title = title,
    artist = artistName,
    thumbnailUrl = "$iiifImageBaseUrl$imageId$imageSize",
)