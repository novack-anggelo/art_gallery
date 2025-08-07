package com.novack.art_gallery.common.data

import com.novack.art_gallery.common.data.model.ArtworkDetailsDTO
import com.novack.art_gallery.common.data.model.OverviewResponseDTO

interface NetworkDataSource {
    suspend fun getArtworksOverview(): OverviewResponseDTO

    suspend fun getArtworkDetails(id: String): ArtworkDetailsDTO
}