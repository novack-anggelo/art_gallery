package com.novack.art_gallery.common.data.model

import kotlinx.serialization.SerialName

data class OverviewResponseDTO(
    val data: List<OverviewDataDTO>,
)

data class OverviewDataDTO(
    val id: Int,
    val title: String,
    @SerialName("image_id") val imageId: String,
    @SerialName("artist_title") val artistName: String,
)
