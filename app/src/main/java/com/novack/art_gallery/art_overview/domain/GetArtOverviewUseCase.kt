package com.novack.art_gallery.art_overview.domain

import com.novack.art_gallery.common.data.ArtRepository
import com.novack.art_gallery.common.domain.Logger
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtOverviewUseCase @Inject constructor(
    private val repository: ArtRepository,
    private val logger: Logger,
) {
    operator fun invoke() = flow {
        emit(ArtworksOverviewState.Loading)
        try {
            val artworksOverview = repository.getArtworksOverview()
            emit(ArtworksOverviewState.Loaded(artworks = artworksOverview))
        } catch (e: Exception) {
            logger.e("GetArtOverviewUseCase", "Error fetching art overview", e)
            emit(ArtworksOverviewState.Error)
        }
    }
        .catch {
            logger.e("GetArtOverviewUseCase", "Error processing art overview", it)
            emit(ArtworksOverviewState.Error)
        }
}