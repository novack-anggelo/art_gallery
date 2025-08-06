package com.novack.art_gallery.common.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OverviewResponseDTO(
    val data: List<OverviewDataDTO>,
)

@JsonClass(generateAdapter = true)
data class OverviewDataDTO(
    val id: Int,
    val title: String?,
    @Json(name = "image_id") val imageId: String?,
    @Json(name = "artist_title") val artistName: String?,
)
