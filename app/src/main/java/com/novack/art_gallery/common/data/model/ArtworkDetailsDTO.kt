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
    @Json(name = "medium_display")val description: String?,
    @Json(name = "date_display") val date: String?,
    @Json(name = "artist_title")val artist: String?,
    @Json(name = "image_id") val imageId: String?,
)
