package com.novack.art_gallery.common.data

import com.novack.art_gallery.art_details.domain.models.ArtworkDetails
import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview

interface ArtRepository {

    suspend fun getArtworksOverview(): List<ArtworkOverview>

    suspend fun getArtworkDetails(id: String): ArtworkDetails
}