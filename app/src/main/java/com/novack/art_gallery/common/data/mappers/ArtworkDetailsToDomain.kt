package com.novack.art_gallery.common.data.mappers

import com.novack.art_gallery.art_details.domain.models.ArtworkDetails
import com.novack.art_gallery.common.data.model.ArtworkDetailsDTO
import com.novack.art_gallery.common.data.model.ArtworkDetailsDataDTO


internal fun ArtworkDetailsDTO.toDomain() = data.toDomain()

private fun ArtworkDetailsDataDTO.toDomain() = ArtworkDetails(
    id = id.toString(),
    title = title,
    description = description,
    date = date,
    artist = artist,
    imageUrl = "$iiifImageBaseUrl$imageId$imageSize",
)