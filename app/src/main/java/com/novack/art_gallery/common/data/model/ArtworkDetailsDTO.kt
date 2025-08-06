package com.novack.art_gallery.common.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtworkDetailsDTO(
    val data: ArtworkDetailsDataDTO,
)

@JsonClass(generateAdapter = true)
data class ArtworkDetailsDataDTO(
    val id: Int,
    val title: String?,
    val description: String?,
    val date: String?,
    val artist: String?,
    @Json(name = "image_id") val imageId: String?,
)
