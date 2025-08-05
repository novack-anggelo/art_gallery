package com.novack.art_gallery.common.data

import com.novack.art_gallery.common.data.model.OverviewResponseDTO

interface NetworkDataSource {
    suspend fun getArtworksOverview(): OverviewResponseDTO
}