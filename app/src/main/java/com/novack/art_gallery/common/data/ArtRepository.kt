package com.novack.art_gallery.common.data

import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview

interface ArtRepository {

    suspend fun getArtworksOverview(): List<ArtworkOverview>
}